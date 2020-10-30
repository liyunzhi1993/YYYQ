package yyyq.ffxiv.entity;

import lombok.Getter;
import lombok.Setter;
import yyyq.common.entity.BaseEntity;

@Getter
@Setter
public class UserFFXIV extends BaseEntity {
    private Long userFfxivId;

    private Long userGameId;

    private String name;

    private Integer clazz;

    private Integer race;

    private Integer server;

    private Integer area;

    private Boolean sex;

    private String introduction;
}