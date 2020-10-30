package yyyq.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import yyyq.auth.entity.UserGame;

@Mapper
public interface UserGameMapper {
    int deleteByPrimaryKey(Long userGameId);

    int insert(UserGame record);

    int insertSelective(UserGame record);

    UserGame selectByPrimaryKey(Long userGameId);

    int updateByPrimaryKeySelective(UserGame record);

    int updateByPrimaryKey(UserGame record);
}