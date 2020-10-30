package yyyq.auth.entity;

import lombok.Getter;
import lombok.Setter;
import yyyq.common.entity.BaseEntity;

import java.util.Date;

@Getter
@Setter
public class Game extends BaseEntity {
    public Long gameId;

    public String gameName;

    public String gameIconUrl;

    public Boolean disabled;
}