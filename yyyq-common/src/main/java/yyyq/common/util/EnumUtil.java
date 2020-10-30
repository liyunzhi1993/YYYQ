package yyyq.common.util;

import yyyq.common.exception.CustomException;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * DemoClassDes
 *
 * @author liyunzhi
 * @date 2018/8/23 16:39
 */
public class EnumUtil {
    public static void isIn(Integer value, Map<Integer,String> map, String errorMessage) {
        if (!map.containsKey(value)) {
            throw new CustomException(errorMessage);
        }
    }
}
