package yyyq.common.enums.wow;

import java.util.HashMap;
import java.util.Map;

/**
 * 部落种族枚举
 *
 * @author liyunzhi
 * @date 2018/8/20 15:25
 */
public enum HordeRaceEnum {
    HYUR(1,"兽人"),
    ELEZEN(2,"牛头人"),
    LALAFELL(3,"亡灵"),
    MIQOTE(4,"巨魔"),
    ROEGADYN(5,"血精灵"),
    AURA(6, "地精"),
    PANDA(6, "熊猫人");

    HordeRaceEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int value;
    public String text;

    public static Map<Integer,String> getMap(){
        Map<Integer, String> map = new HashMap<>();
        for (HordeRaceEnum item : HordeRaceEnum.values()
                ) {
            map.put(item.value,item.text);
        }
        return map;
    }
}
