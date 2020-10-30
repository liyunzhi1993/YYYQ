package yyyq.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auth 
 */
public class PasswordUtil {
    public static boolean isValid(String password) {
        if (password.length() < 8) {
            return false;
        }
        if (password.length() > 0) {
            //判断是否有空格字符串
            for (int t = 0; t < password.length(); t++) {
                String b = password.substring(t, t + 1);
                if (b.equals(" ")) {
                    return false;
                }
            }
            //判断是否有汉字
            int count = 0;
            String regEx = "[\\u4e00-\\u9fa5]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(password);
            while (m.find()) {
                for (int i = 0; i <= m.groupCount(); i++) {
                    count = count + 1;
                }
            }

            if (count > 0) {
                return false;
            }
            //判断是否是字母和数字
            boolean hasDigit=false;
            boolean hasLetter=false;
            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);
                if (!Character.isLetterOrDigit(c)) {
                    return false;
                }
                if (Character.isDigit(c)) {
                    hasDigit=true;
                }
                if (Character.isLetter(c)) {
                    hasLetter=true;
                }
            }
            if (hasDigit && hasLetter) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }
}