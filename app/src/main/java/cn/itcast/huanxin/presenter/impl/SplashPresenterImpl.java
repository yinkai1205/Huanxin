package cn.itcast.huanxin.presenter.impl;

import com.hyphenate.chat.EMClient;

import cn.itcast.huanxin.presenter.SplashPresenter;
import cn.itcast.huanxin.view.inter.SplashView;

/**
 * Created by Kevin.
 */
public class SplashPresenterImpl implements SplashPresenter {

    public SplashView mView;

    public SplashPresenterImpl(SplashView view) {
        mView = view;
    }

    @Override
    public void checkLogin() {
        if (EMClient.getInstance().isLoggedInBefore() && EMClient.getInstance().isConnected()) {
            //已登录
            mView.isLogin(true);
        } else {
            //未登录
            mView.isLogin(false);
        }
    }
}
