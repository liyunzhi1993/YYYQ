package yyyq.auth.service;

import yyyq.auth.entity.Game;
import yyyq.auth.entity.GameNav;
import yyyq.common.model.auth.GameNavModel;

import java.util.List;

/**
 * GameService
 *
 * @author liyunzhi
 * @date 2018/8/7 14:40
 */
public interface GameService {
    /**
     * 获取游戏列表
     * @return
     */
    List<Game> getGameList();

    /**
     * 根据用户ID获取游戏列表
     * @param userId
     * @return
     */
    List<Game> getGameListByUserId(long userId);

    /**
     * 根据用户ID获取游戏导航
     * @param userId
     * @return
     */
    List<GameNavModel> getGameNavList(long userId);
}
