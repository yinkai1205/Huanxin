package cn.itcast.huanxin.view;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.itcast.huanxin.R;
import cn.itcast.huanxin.base.BaseActivity;
import cn.itcast.huanxin.model.User;
import cn.itcast.huanxin.presenter.RegisterPresenter;
import cn.itcast.huanxin.presenter.impl.RegisterPresenterImpl;
import cn.itcast.huanxin.utils.StringUtils;
import cn.itcast.huanxin.view.inter.RegisterView;

public class RegisterActivity extends BaseActivity implements RegisterView {

    @InjectView(R.id.iv_avatar)
    ImageView ivAvatar;
    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.btn_register)
    Button btnRegister;

    private RegisterPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);

        mPresenter = new RegisterPresenterImpl(this);
        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_GO) {
                    regist();
                    return true;
                }
                return false;
            }
        });
    }

    public void regist() {
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
            mPresenter.startRegister(username, password);
        }
    }

    @OnClick(R.id.btn_register)
    public void onClick() {
        regist();
    }

    @Override
    public void onRegisterFinish(User user, boolean success, String errMsg) {
        if (success) {
            showToast("注册成功!");
            save(user);
            startActivity(LoginActivity.class, true);
        } else {
            showToast("注册失败:" + errMsg);
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
}
