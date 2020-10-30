package yyyq.common.enums.ffxiv;

import java.util.HashMap;
import java.util.Map;

/**
 * 职业
 *
 * @author liyunzhi
 * @date 2018/8/20 15:25
 */
public enum ClassEnum {
    WARRIOR(1,"战士"),
    PALADIN(3,"骑士"),
    DARKKNIGHT(5,"暗黑骑士"),
    SCHOLAR(7,"学者"),
    WHITEMAGE(9,"白魔法师"),
    ASTROLOGIAN(11,"占星术士"),
    DRAGOON(13,"龙骑士"),
    MONK(15,"武僧"),
    NINJA(17,"忍者"),
    SAMURAI(19,"武士"),
    BARD(21,"吟游诗人"),
    MACHINIST(23,"机工士"),
    BLACKMAGE(25,"黑魔法师"),
    SUMMONER(27,"召唤师"),
    REDMAGE(29,"赤魔法师");

    ClassEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int value;
    public String text;

    public static Map<Integer,String> getMap(){
        Map<Integer, String> map = new HashMap<>();
        for (ClassEnum item :ClassEnum.values()
                ) {
            map.put(item.value,item.text);
        }
        return map;
    }
}
