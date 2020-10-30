package yyyq.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import yyyq.auth.entity.Game;

import java.util.List;

@Mapper
public interface GameMapper {
    int deleteByPrimaryKey(Long gameId);

    int insert(Game record);

    int insertSelective(Game record);

    Game selectByPrimaryKey(Long gameId);

    int updateByPrimaryKeySelective(Game record);

    int updateByPrimaryKey(Game record);

    /**
     * 根据用户ID获取游戏列表
     * @param userId
     * @return
     */
    List<Game> getGameListByUserId(long userId);
}