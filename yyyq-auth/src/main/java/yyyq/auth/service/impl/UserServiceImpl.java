package yyyq.auth.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import yyyq.auth.entity.User;
import yyyq.auth.entity.UserGame;
import yyyq.common.exception.CustomException;
import yyyq.auth.mapper.UserGameMapper;
import yyyq.auth.mapper.UserMapper;
import yyyq.auth.model.EditUserModel;
import yyyq.auth.model.LoginUserModel;
import yyyq.auth.model.RegisterUserModel;
import yyyq.auth.security.JwtTokenProvider;
import yyyq.auth.service.MessageService;
import yyyq.auth.service.UserService;
import yyyq.common.enums.SexEnum;
import yyyq.common.util.PasswordUtil;
import yyyq.common.util.PhoneUtil;
import yyyq.common.util.StringUtil;
import yyyq.external.annotation.RedisCacheGet;
import yyyq.external.service.QiniuClientService;
import yyyq.external.service.RedisClientService;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * UserServiceImpl
 *
 * @author liyunzhi
 * @date 2018/7/10 15:22
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RedisClientService redisClientService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private QiniuClientService qiniuClientService;

    @Autowired
    private UserGameMapper userGameMapper;

    private static final String redisPrefix="YYYQ-AUTH";


    private static final String [] imageType={".jpg",".png"};

    private static final String imagePrefix="avatar_";


    @Value("${yyyq.default.avatar.url}")
    private String defaultAvatarUrl;

    @Value("${yyyq.default.banner.url}")
    private String defaultBannerUrl;


    /**
     * 登录
     *
     * @param loginUserModel
     * @return
     */
    @Override
    public String login(LoginUserModel loginUserModel){
        loginUserModel.userName=loginUserModel.phone;
        if (StringUtil.isNullOrWhiteSpace(loginUserModel.userName)) {
            throw new CustomException("用户名不能为空");
        }
        if (StringUtil.isNullOrWhiteSpace(loginUserModel.password)) {
            throw new CustomException("密码不能为空");
        }
        User loginUser = userMapper.selectByUserName(loginUserModel.userName);
        if (loginUser == null) {
            throw new CustomException("用户不存在");
        } else {
            if (bCryptPasswordEncoder.matches(loginUserModel.password,loginUser.password)) {
                //验证有效期为1天，如果记住我，则是30天
                long validityInMilliseconds=86400000;
                if (loginUserModel.rememberMe.equals("on")) {
                    validityInMilliseconds*=7;
                }
                //缓存登录数据到redis
                redisClientService.setStringToRedis(redisPrefix +":"+ loginUser.userId, JSON.toJSONString(loginUser), validityInMilliseconds/60);
                return jwtTokenProvider.createToken(loginUser.userId.toString(), loginUser.userName,validityInMilliseconds);
            } else {
                throw new CustomException("用户名/密码错误");
            }
        }
    }

    /**
     * 注册
     *
     * @param registerUserModel
     * @return
     */
    @Override
    public String register(RegisterUserModel registerUserModel) {
        if (!PhoneUtil.isChinaPhoneLegal(registerUserModel.phone)) {
            throw new CustomException("手机号错误");
        }
        if (!PasswordUtil.isValid(registerUserModel.password)) {
            throw new CustomException("密码需要至少8位数字和字母组成");
        }
        //根据手机号校验验证码
        if (!messageService.checkRegisterCode(registerUserModel.phone, registerUserModel.code)) {
            throw new CustomException("验证码错误");
        }
        //用户名默认密码
        registerUserModel.userName=registerUserModel.phone;
        if (userMapper.selectByUserName(registerUserModel.userName)==null) {
            registerUserModel.password=bCryptPasswordEncoder.encode(registerUserModel.password);
            //设置默认
            registerUserModel.nickName = registerUserModel.userName + String.valueOf(new Random().nextInt(99));
            registerUserModel.avatar=defaultAvatarUrl;
            registerUserModel.banner=defaultBannerUrl;
            registerUserModel.sex= SexEnum.MALE.value;
            if (userMapper.insert(registerUserModel) > 0) {
                User registerUser = userMapper.selectByUserName(registerUserModel.userName);
                long validityInMilliseconds=86400000;

                //缓存登录数据到redis
                redisClientService.setStringToRedis(redisPrefix +":"+ registerUser.userId, JSON.toJSONString(registerUser), validityInMilliseconds/60);

                //目前将ff14插入用户关联游戏中，后续要改，让用户自己选
                UserGame userGame=new UserGame();
                userGame.userId=registerUser.userId;
                userGame.gameId=Long.parseLong("1");
                userGameMapper.insert(userGame);

                return jwtTokenProvider.createToken(registerUser.userId.toString(), registerUser.userName,validityInMilliseconds);
            } else {
                throw new CustomException("注册异常，请稍后重试");
            }
        } else {
            throw new CustomException("手机号已存在");
        }
    }

    /**
     * 编辑用户信息
     *
     * @param editUserModel
     * @return
     */
    @Override
    public boolean editUser(EditUserModel editUserModel) {
        if (StringUtil.isNullOrWhiteSpace(editUserModel.nickName)) {
            throw new CustomException("昵称不能为空");
        }
        User editUser = userMapper.selectByPrimaryKey(editUserModel.userId);
        if (editUser == null) {
            throw new CustomException("用户不存在");
        } else {
            editUser.nickName=editUserModel.nickName;
            editUser.sex=editUserModel.sex;
            if (userMapper.updateByPrimaryKey(editUser) > 0) {
                refreshUserRedis(editUser.userId);
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 根据用户名获取用户
     *
     * @param userId
     * @return
     */
    @Override
    @RedisCacheGet(key=redisPrefix+":@yyyq.auth.entity.User#userId")
    public User getUserByUserId(long userId) {
        User user=userMapper.selectByPrimaryKey(userId);
        if (user==null) {
            throw new CustomException("找不到该用户");
        }
        return user;
    }

    /**
     * 刷新用户缓存
     *
     * @param userId
     */
    @Override
    public void refreshUserRedis(long userId) {
        User user=userMapper.selectByPrimaryKey(userId);
        if (user!=null) {
            redisClientService.setStringToRedis(redisPrefix + ":" + userId, JSON.toJSONString(user));
        }
    }

    /**
     * 上传头像
     *
     * @param fileName
     * @param fileByte
     * @return
     */
    @Override
    public String uploadAvatar(String fileName, byte[] fileByte,long userId) {
        String type = fileName.substring(fileName.lastIndexOf("."));
        if (!Arrays.asList(imageType).contains(type)) {
            throw new CustomException("图片格式只支持jpg/png类型，请重新上传");
        }
        String url=qiniuClientService.uploadImage(imagePrefix+String.valueOf(getUserByUserId(userId).userId)+String.valueOf(new Random().nextInt(99)+(new Date()).getTime())+type,fileByte);
        if (StringUtil.isNotNullOrEmpty(url)) {
            User user=userMapper.selectByPrimaryKey(userId);
            user.avatar=url;
            userMapper.updateByPrimaryKey(user);
            refreshUserRedis(user.userId);
        }
        return url;
    }
}
