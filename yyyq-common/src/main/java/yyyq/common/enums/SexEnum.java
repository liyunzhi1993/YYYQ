package yyyq.common.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 性别枚举
 *
 * @author liyunzhi
 * @date 2018/7/31 10:28
 */
@Getter
public enum  SexEnum {
    FEMALE(new Byte("0"),"女"),
    MALE(new Byte("1"), "男");

    SexEnum(byte value, String text) {
        this.value = value;
        this.text = text;
    }

    public byte value;
    public String text;

    public static Map<Integer,String> getMap(){
        Map<Integer, String> map = new HashMap<>();
        for (SexEnum item :SexEnum.values()
                ) {
            map.put(Integer.parseInt(String.valueOf(item.value)),item.text);
        }
        return map;
    }
}
