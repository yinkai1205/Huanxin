package cn.itcast.huanxin.view.inter;

import cn.itcast.huanxin.model.User;

/**
 * Created by Kevin.
 */
public interface RegisterView {

    void onRegisterFinish(User user, boolean success, String errMsg);

    void showProgressDialog(String s);

    void hideProgressDialog();
}
