package yyyq.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class StringUtil {

    /**
     * String是否为空
     *
     * @param str
     * @return 返回值
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * String是否为非空
     *
     * @param str
     * @return 返回值
     */
    public static boolean isNotNullOrEmpty(String str) {
        return str != null && str.length() > 0;
    }

    public static boolean isNullOrWhiteSpace(String value) {
        if (value == null) {
            return true;
        }

        for (int i = 0; i < value.length(); i++) {
            if (!Character.isWhitespace(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotNullOrWhiteSpace(String value) {
        return !isNullOrWhiteSpace(value);
    }

    public static boolean equals(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }
        return str1.equals(str2);
    }

    public static boolean ignoreCaseEquals(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }

        return str1.equalsIgnoreCase(str2);
    }

    public static String toLowerCase(String str) {
        if (isNullOrEmpty(str)) {
            return "";
        }
        return str.toLowerCase();
    }

    public static String toUpperCase(String str) {
        if (isNullOrEmpty(str)) {
            return "";
        }
        return str.toUpperCase();
    }

    public static boolean isDecimalString(String decimalStr) {
        if (StringUtil.isNullOrEmpty(decimalStr)) {
            return false;
        }
        try {
            BigDecimal decimal = new BigDecimal(decimalStr);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static String formatDecimal(String decimalStr) {
        return formatDecimal(decimalStr, "0");
    }

    public static String formatDecimal(String decimalStr, String deafultValue) {
        if (StringUtil.isNullOrEmpty(decimalStr)) {
            return deafultValue;
        }
        try {
            BigDecimal decimal = new BigDecimal(decimalStr);
            return decimal.toString();
        } catch (Exception ex) {
            return deafultValue;
        }
    }

    public static boolean isGuidString(String idStr) {
        if (StringUtil.isNullOrEmpty(idStr)) {
            return false;
        }
        try {
            String rexp = "[?a-zA-Z0-9]{8}-[?a-zA-Z0-9]{4}-[?a-zA-Z0-9]{4}-[?a-zA-Z0-9]{4}-[?a-zA-Z0-9]{12}";
            return idStr.matches(rexp);
        } catch (Exception ex) {
            return false;
        }
    }

    public static String decimalToString(BigDecimal d, int degree) {
        if (d == null) {
            return null;
        }
        if (degree < 0) {
            return "精度设置错误！";
        }

        String pattern = "";
        if (degree >= 1) {
            StringBuilder builder = new StringBuilder();
            builder.append("0.");
            for (int i = 0; i < degree; i++) {
                builder.append("0");
            }
            pattern = builder.toString();
        }
        DecimalFormat df1 = new DecimalFormat(pattern);
        return df1.format(d);
    }
}
