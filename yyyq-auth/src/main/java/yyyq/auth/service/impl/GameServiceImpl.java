package yyyq.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yyyq.auth.entity.Game;
import yyyq.auth.entity.GameNav;
import yyyq.auth.mapper.GameMapper;
import yyyq.auth.mapper.GameNavMapper;
import yyyq.auth.service.GameService;
import yyyq.common.model.auth.GameNavModel;
import yyyq.external.annotation.RedisCacheGet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DemoClassDes
 *
 * @author liyunzhi
 * @date 2018/8/7 14:43
 */
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private GameNavMapper gameNavMapper;

    private static final String redisPrefix="YYYQ-AUTH";

    /**
     * 获取游戏列表
     *
     * @return
     */
    @Override
    public List<Game> getGameList() {
        return null;
    }

    /**
     * 根据用户ID获取游戏列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<Game> getGameListByUserId(long userId) {
        return gameMapper.getGameListByUserId(userId);
    }

    /**
     * 根据用户ID获取游戏导航
     *
     * @param userId
     * @return
     */
    @Override
    @RedisCacheGet(key=redisPrefix+"-GAMENAV"+":@java.util.List#userId")
    public List<GameNavModel> getGameNavList(long userId) {
        List<GameNavModel> gameNavModelList = new ArrayList<>();
        List<GameNav> gameNavList = gameNavMapper.selectGameNavList();
        for (GameNav gameNav : gameNavList) {
            GameNavModel gameNavModel = new GameNavModel();
            gameNavModel.gameNavName = gameNav.gameNavName;
            gameNavModel.gameNavUrl = gameNav.gameNavUrl;
            gameNavModelList.add(gameNavModel);
        }
        return gameNavModelList;
    }
}
