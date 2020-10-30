package yyyq.auth.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGame {
    public Long userGameId;

    public Long userId;

    public Long gameId;

    public Boolean disabled;
}