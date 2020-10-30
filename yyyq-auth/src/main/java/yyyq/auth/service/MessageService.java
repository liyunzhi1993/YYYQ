package yyyq.auth.service;

/**
 * MessageService
 *
 * @author liyunzhi
 * @date 2018/7/25 17:42
 */
public interface MessageService {
    /**
     * 发送注册验证码
     * @param phone
     * @return
     */
    boolean sendRegisterCode(String phone);

    /**
     * 校验注册验证码
     * @param phone
     * @return
     */
    boolean checkRegisterCode(String phone, String code);

    /**
     * 获取注册短信验证码
     * @param phone
     * @return
     */
    String getRegisterCode(String phone);

    /**
     * 发送短信
     * @param phone
     * @return
     */
    boolean sendSms(String phone,String templateCode,String templateContent);
}
