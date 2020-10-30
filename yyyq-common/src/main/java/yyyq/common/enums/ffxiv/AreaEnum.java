package yyyq.common.enums.ffxiv;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务器
 *
 * @author liyunzhi
 * @date 2018/8/20 15:25
 */
public enum AreaEnum {
    CHINA_SD(1,"国服"),
    CHINA_TX(2,"国服二区"),
    INTERNATIONAL(3,"国际服");

    AreaEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int value;
    public String text;

    public static Map<Integer,String> getMap(){
        Map<Integer, String> map = new HashMap<>();
        for (AreaEnum item :AreaEnum.values()
                ) {
            map.put(item.value,item.text);
        }
        return map;
    }
}
