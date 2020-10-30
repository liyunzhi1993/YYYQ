package yyyq.common.enums.wow;

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
    DEATHKNIGHT(3,"死亡骑士"),
    DARKKNIGHT(5,"盗贼"),
    DEMONHUNTER(7,"恶魔猎手"),
    PALADIN(9,"圣骑士"),
    MONK(11,"武僧"),
    DRUID(13,"德鲁伊"),
    WARLOCK(15,"术士"),
    PRIEST(17,"牧师"),
    HUNTER(19,"猎人"),
    MAGE(21,"法师"),
    SHAMAN(23,"萨满");

    ClassEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int value;
    public String text;

    public static Map<Integer,String> getMap(){
        Map<Integer, String> map = new HashMap<>();
        for (ClassEnum item : ClassEnum.values()
                ) {
            map.put(item.value,item.text);
        }
        return map;
    }
}
