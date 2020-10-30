package yyyq.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import yyyq.auth.entity.User;

import java.util.Map;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    User selectByUserName(String userName);

    User selectByUserNamePassword(Map map);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}