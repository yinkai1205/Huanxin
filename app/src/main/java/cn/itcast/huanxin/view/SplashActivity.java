package cn.itcast.huanxin.view;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.bmob.v3.Bmob;
import cn.itcast.huanxin.R;
import cn.itcast.huanxin.base.BaseActivity;
import cn.itcast.huanxin.presenter.SplashPresenter;
import cn.itcast.huanxin.presenter.impl.SplashPresenterImpl;
import cn.itcast.huanxin.view.inter.SplashView;

public class SplashActivity extends BaseActivity implements SplashView {

    @InjectView(R.id.iv_logo)
    ImageView ivLogo;
    private SplashPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);

        //第一：默认初始化
        Bmob.initialize(this, "922eb1c3c02c90b98887f55609762719");

        mPresenter = new SplashPresenterImpl(this);
        mPresenter.checkLogin();
    }

    @Override
    public void isLogin(final boolean isLogin) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLogin) {
                    showToast("跳转到主页面");
                    startActivity(MainActivity.class, true);
                } else {
                    showToast("跳转到登录页面");
                    startActivity(LoginActivity.class, true);
                }
            }
        }, 2000);
    }
}
