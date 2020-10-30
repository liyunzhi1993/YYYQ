package yyyq.common.constants;

/**
 * SecurityConstants
 *
 * @author liyunzhi
 * @date 2018/7/12 11:13
 */
public class SecurityConstants {
    public static final String SECRET = "SecretKeyYYYQJWTs";
    public static final long EXPIRATION_TIME = 864_000_000;
    public static final String TOKEN_PREFIX = "YYYQ";
    public static final String HEADER_STRING = "YYYQUSERTOKEN";
    public static final String COOKIE_NAME = "YYYQ_USER";
    public static final String LOGIN_URL = "/user/join";
    public static final String[] NOT_AUTH_PAGE = {"/user/login","/user/register","/message/sendRegisterCode"};
}
