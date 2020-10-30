package yyyq.auth.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yyyq.common.exception.CustomException;
import yyyq.auth.service.MessageService;
import yyyq.common.constants.SmsTemplateConstants;
import yyyq.common.util.PhoneUtil;
import yyyq.common.util.StringUtil;
import yyyq.external.service.RedisClientService;

import java.util.Random;

/**
 * MessageServiceImpl
 *
 * @author liyunzhi
 * @date 2018/7/25 17:45
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private RedisClientService redisClientService;

    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    // static final String accessKeyId = "AhCyCrBZ5kuUkmwE";
    static final String accessKeyId = "123";
    // static final String accessKeySecret = "4nk9PBjvfXhUHjP23lVwVbLhmUPctm";
    static final String accessKeySecret = "456";

    static final String signName = "李允智";

    private static final String redisPrefix="YYYQ-AUTH-REGISTER-CODE";


    @Override
    public boolean sendRegisterCode(String phone) {
        if (!PhoneUtil.isChinaPhoneLegal(phone)) {
            throw new CustomException("手机号错误");
        }
        String code = String.valueOf(new Random().nextInt(999999));
        if (StringUtil.isNotNullOrEmpty(getRegisterCode(phone))) {
            throw new CustomException("验证码已发送，请留意短信");
        }
        try {
            if (sendSms(phone, SmsTemplateConstants.REGISTER_CODE, String.format("{\"code\":\"%s\"}", code))) {
                redisClientService.setStringToRedis(redisPrefix +":"+ phone, code, 5*60);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            throw new CustomException("发送短信失败，请稍后重试");
        }
    }

    /**
     * 校验注册验证码
     *
     * @param phone
     * @param code
     * @return
     */
    @Override
    public boolean checkRegisterCode(String phone, String code) {
        String redisCode=getRegisterCode(phone);
        if (code.equals(redisCode)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取注册短信验证码
     *
     * @param phone
     * @return
     */
    @Override
    public String getRegisterCode(String phone) {
        return redisClientService.getStringFromRedis(redisPrefix+":"+phone);
    }



    /**
     * 发送短信
     *
     * @param phone
     * @return
     */
    @Override
    public boolean sendSms(String phone,String templateCode,String templateContent) {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IAcsClient acsClient = null;
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            acsClient = new DefaultAcsClient(profile);
        } catch (ClientException ex) {
            return false;
        }

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(templateContent);

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        try {
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            return sendSmsResponse.getCode().equals("OK");
        } catch (Exception ex) {
            return false;
        }
    }
}
