package yyyq.ffxiv.mapper;

import org.apache.ibatis.annotations.Mapper;
import yyyq.ffxiv.entity.UserFFXIV;

@Mapper
public interface UserFFXIVMapper {
    int deleteByPrimaryKey(Long userFfxivId);

    int insert(UserFFXIV record);

    int insertSelective(UserFFXIV record);

    UserFFXIV selectByPrimaryKey(Long userFfxivId);

    UserFFXIV selectByUserGameId(Long userGameId);

    int updateByPrimaryKeySelective(UserFFXIV record);

    int updateByPrimaryKeyWithBLOBs(UserFFXIV record);

    int updateByPrimaryKey(UserFFXIV record);
}