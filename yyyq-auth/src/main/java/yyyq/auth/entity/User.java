package yyyq.auth.entity;

import lombok.Getter;
import lombok.Setter;
import yyyq.common.entity.BaseEntity;


@Getter
@Setter
public class User extends BaseEntity {
    public Long userId;

    public String userName;

    public String password;

    public String nickName;

    public String email;

    public String phone;

    public Byte sex;

    public String avatar;

    public String banner;

    public String wechatOpenId;
}