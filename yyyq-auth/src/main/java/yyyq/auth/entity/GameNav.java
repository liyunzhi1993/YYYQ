package yyyq.auth.entity;

import lombok.Getter;
import lombok.Setter;
import yyyq.common.entity.BaseEntity;

import java.util.Date;

@Setter
@Getter
public class GameNav extends BaseEntity {
    public Long gameNavId;

    public String gameNavName;

    public Long parentGameNavId;

    public String gameNavIconUrl;

    public String gameNavUrl;

    public Integer orderBy;

    public Boolean disabled;

    public Long gameId;
}