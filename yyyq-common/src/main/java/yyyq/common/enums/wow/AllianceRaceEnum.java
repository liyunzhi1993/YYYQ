package yyyq.common.enums.wow;

import java.util.HashMap;
import java.util.Map;

/**
 * 联盟种族枚举
 *
 * @author liyunzhi
 * @date 2018/8/20 15:25
 */
public enum AllianceRaceEnum {
    HYUR(1,"人类"),
    ELEZEN(2,"矮人"),
    LALAFELL(3,"侏儒"),
    MIQOTE(4,"暗夜精灵"),
    ROEGADYN(5,"德莱尼"),
    AURA(6, "狼人");

    AllianceRaceEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int value;
    public String text;

    public static Map<Integer,String> getMap(){
        Map<Integer, String> map = new HashMap<>();
        for (AllianceRaceEnum item : AllianceRaceEnum.values()
                ) {
            map.put(item.value,item.text);
        }
        return map;
    }
}
