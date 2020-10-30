package yyyq.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import yyyq.auth.entity.GameNav;

import java.util.List;

@Mapper
public interface GameNavMapper {
    int deleteByPrimaryKey(Long gameNavId);

    int insert(GameNav record);

    int insertSelective(GameNav record);

    GameNav selectByPrimaryKey(Long gameNavId);

    int updateByPrimaryKeySelective(GameNav record);

    int updateByPrimaryKey(GameNav record);

    List<GameNav> selectGameNavList();
}