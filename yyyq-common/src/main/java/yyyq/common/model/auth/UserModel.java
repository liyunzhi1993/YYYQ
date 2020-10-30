package yyyq.common.model.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * UserModel
 *
 * @author liyunzhi
 * @date 2018/7/12 19:52
 */
@Getter
@Setter
public class UserModel {
    public long userId;
    public String userName;
    public String avatar;
    public String nickName;
    public Byte sex;
    public String banner;
    public List<GameNavModel> gameNavModelList;
}
