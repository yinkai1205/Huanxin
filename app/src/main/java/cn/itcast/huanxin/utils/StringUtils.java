package cn.itcast.huanxin.utils;

/**
 * Created by Kevin.
 */
public class StringUtils {

    //用户名: 第一位必须是字母, 3-20位
    public static boolean checkUsername(String username) {
        if (username != null) {
            if (username.matches("^[a-zA-Z]\\w{2,19}$")) {
                return true;
            }
        }

        return false;
    }

    //密码: 必须都是数字,3-20位
    public static boolean checkPassword(String password) {
        if (password != null) {
            if (password.matches("^[0-9]{3,20}$")) {
                return true;
            }
        }

        return false;
    }
}
