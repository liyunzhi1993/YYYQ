package yyyq.auth.service;

import yyyq.auth.entity.User;
import yyyq.auth.model.EditUserModel;
import yyyq.auth.model.LoginUserModel;
import yyyq.auth.model.RegisterUserModel;

/**
 * UserService
 *
 * @author liyunzhi
 * @date 2018/7/12 19:45
 */
public interface UserService {

    /**
     * 登录
     * @param loginUserModel
     * @return
     */
    String login(LoginUserModel loginUserModel);

    /**
     * 注册
     * @param registerUserModel
     * @return
     */
    String register(RegisterUserModel registerUserModel);

    /**
     * 编辑用户信息
     * @param editUserModel
     * @return
     */
    boolean editUser(EditUserModel editUserModel);

    /**
     * 根据用户名获取用户
     * @param userId
     * @return
     */
    User getUserByUserId(long userId);

    /**
     * 刷新用户缓存
     * @param userId
     */
    void refreshUserRedis(long userId);

    /**
     * 上传头像
     * @param fileName
     * @param fileByte
     * @param userId
     * @return
     */
    String uploadAvatar(String fileName, byte[] fileByte,long userId);

}
