package yyyq.ffxiv.service;

import yyyq.common.model.auth.UserModel;
import yyyq.ffxiv.entity.UserFFXIV;
import yyyq.ffxiv.model.select.UserFFXIVSelectListModel;

import java.util.List;

/**
 * UserService
 *
 * @author liyunzhi
 * @date 2018/7/12 19:45
 */
public interface UserFFXIVService {

    /**
     * 获取用户的游戏信息
     * @return
     */
    UserFFXIV getUserFFXIV(UserModel userModel);

    /**
     * 保存用户的游戏信息
     * @param userFFXIV
     * @return
     */
    boolean saveUserFFXIV(UserFFXIV userFFXIV, UserModel userModel);

    /**
     * 获取所有下拉绑定列表
     * @return
     */
    UserFFXIVSelectListModel getUserFFXIVSelectList();
}
