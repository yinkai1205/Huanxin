package cn.itcast.huanxin.view;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.itcast.huanxin.R;
import cn.itcast.huanxin.base.BaseActivity;
import cn.itcast.huanxin.presenter.LoginPresenter;
import cn.itcast.huanxin.presenter.impl.LoginPresenterImpl;
import cn.itcast.huanxin.utils.StringUtils;
import cn.itcast.huanxin.view.inter.LoginView;

public class LoginActivity extends BaseActivity implements LoginView {

    @InjectView(R.id.iv_avatar)
    ImageView ivAvatar;
    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        mPresenter = new LoginPresenterImpl(this);
        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_GO) {
                    login();
                    return true;
                }
                return false;
            }
        });
    }

    private void login() {
        final String username = etUsername.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        //用户名密码合法性校验
        //用户名: 第一位必须是字母, 3-20位
        //密码: 必须都是数字,3-20位
        if (!StringUtils.checkUsername(username)) {
            showToast("用户名第一位必须是字母, 3-20位");
        } else if (!StringUtils.checkPassword(password)) {
            showToast("密码必须都是数字,3-20位");
        } else {
            mPresenter.startLogin(username, password);
        }
    }

    @OnClick({R.id.btn_login, R.id.tv_new_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_new_user:
                startActivity(RegisterActivity.class, true);
                break;
        }
    }

    @Override
    public void showProgressDialog(String s) {
        showDialog(s, true);
    }

    @Override
    public void hideProgressDialog() {
        hideDialog();
    }

    @Override
    public void onLoginFinish(boolean success, String errMsg) {
        if (success) {
            showToast("登录成功!");
            startActivity(MainActivity.class, true);
        } else {
            showToast("登录失败:" + errMsg);
        }
    }
}
