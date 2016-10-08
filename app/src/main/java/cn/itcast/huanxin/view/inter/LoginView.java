package cn.itcast.huanxin.view.inter;

/**
 * Created by Kevin.
 */
public interface LoginView {

    void showProgressDialog(String s);

    void hideProgressDialog();

    void onLoginFinish(boolean success, String errMsg);
}
