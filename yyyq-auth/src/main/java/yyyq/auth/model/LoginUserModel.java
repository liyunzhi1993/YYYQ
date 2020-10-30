package yyyq.auth.model;

import lombok.Getter;
import lombok.Setter;
import yyyq.auth.entity.User;

/**
 * DemoClassDes
 *
 * @author liyunzhi
 * @date 2018/7/29 17:03
 */
@Getter
@Setter
public class LoginUserModel extends User {
    public String rememberMe="";
}
