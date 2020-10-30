package yyyq.ffxiv.entity;

import lombok.Getter;
import lombok.Setter;
import yyyq.common.entity.BaseEntity;

@Setter
@Getter
public class UserGame {
    private Long userGameId;

    private Long userId;

    private Long gameId;

    private Boolean disabled;
}