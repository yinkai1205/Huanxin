package cn.itcast.huanxin.presenter.impl;

import com.hyphenate.chat.EMClient;

import cn.itcast.huanxin.adapter.EMCallBackAdapter;
import cn.itcast.huanxin.presenter.LoginPresenter;
import cn.itcast.huanxin.utils.ThreadUtils;
import cn.itcast.huanxin.view.inter.LoginView;

/**
 * Created by Kevin.
 */
public class LoginPresenterImpl implements LoginPresenter {

    public LoginView mView;

    public LoginPresenterImpl(LoginView view) {
        mView = view;
    }

    @Override
    public void startLogin(String username, String password) {
        mView.showProgressDialog("正在登录,请稍候...");

        EMClient.getInstance().login(username, password, new EMCallBackAdapter() {
            @Override
            public void onSuccess() {
                mView.onLoginFinish(true, null);
                ThreadUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.hideProgressDialog();
                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                mView.onLoginFinish(false, s);
                ThreadUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.hideProgressDialog();
                    }
                });
            }
        });
    }
}
