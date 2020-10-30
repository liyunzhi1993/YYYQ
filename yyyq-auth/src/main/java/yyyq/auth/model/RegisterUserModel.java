package yyyq.auth.model;

import lombok.Getter;
import lombok.Setter;
import yyyq.auth.entity.User;

/**
 * RegisterUserModel
 *
 * @author liyunzhi
 * @date 2018/7/29 14:34
 */
@Getter
@Setter
public class RegisterUserModel extends User {
    public String code;
}
