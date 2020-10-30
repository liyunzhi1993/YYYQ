package yyyq.common.enums.ffxiv;

import java.util.HashMap;
import java.util.Map;

/**
 * 盛大服务器
 *
 * @author liyunzhi
 * @date 2018/8/20 15:25
 */
public enum ChinaSDServerEnum {
    PURPLEWATER(1,"紫水栈桥"),
    LANOSIA(2,"拉诺西亚"),
    MODUNA(3,"摩杜纳"),
    MIQOTE(4,"幻影群岛"),
    PHANTOMISLAND(5,"萌芽池"),
    YANXIA(6,"延夏"),
    LANDOFDIVINITY(7,"神意之地"),
    MANOR(8,"静语庄园"),
    RUBYSEA(9,"红玉海");

    ChinaSDServerEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int value;
    public String text;

    public static Map<Integer,String> getMap(){
        Map<Integer, String> map = new HashMap<>();
        for (ChinaSDServerEnum item :ChinaSDServerEnum.values()
                ) {
            map.put(item.value,item.text);
        }
        return map;
    }
}
