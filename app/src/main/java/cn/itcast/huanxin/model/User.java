package cn.itcast.huanxin.model;

import cn.bmob.v3.BmobUser;

/**
 * Created by Kevin.
 */
public class User extends BmobUser {

    public String pass;

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
        this.pass = password;
    }

    public String getPassword() {
        return pass;
    }
}
