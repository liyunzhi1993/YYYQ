package yyyq.common.enums.ffxiv;

import java.util.HashMap;
import java.util.Map;

/**
 * 种族枚举
 *
 * @author liyunzhi
 * @date 2018/8/20 15:25
 */
public enum RaceEnum {
    HYUR(1,"人类"),
    ELEZEN(2,"精灵"),
    LALAFELL(3,"拉拉菲尔"),
    MIQOTE(4,"猫魅"),
    ROEGADYN(5,"鲁加"),
    AURA(6, "敖龙");

    RaceEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int value;
    public String text;

    public static Map<Integer,String> getMap(){
        Map<Integer, String> map = new HashMap<>();
        for (RaceEnum item :RaceEnum.values()
                ) {
            map.put(item.value,item.text);
        }
        return map;
    }
}
