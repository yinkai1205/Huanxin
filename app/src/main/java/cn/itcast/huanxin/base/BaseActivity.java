package cn.itcast.huanxin.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.itcast.huanxin.model.User;
import cn.itcast.huanxin.utils.ToastUtils;

/**
 * Created by Kevin.
 */
public class BaseActivity extends AppCompatActivity {

    public Handler mHandler = new Handler();
    public ProgressDialog mDialog;
    public SharedPreferences mPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialog = new ProgressDialog(this);
        mPref = getSharedPreferences("config", MODE_PRIVATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDialog.dismiss();//dismiss会释放dialog的资源
    }

    public void startActivity(Class clazz, boolean isFinish) {
        startActivity(new Intent(this, clazz));
        if (isFinish) {
            finish();
        }
    }

    public void showToast(String msg) {
        ToastUtils.showToast(this, msg);
    }

    public void showDialog(String msg, boolean cancelable) {
        mDialog.setMessage(msg);
        mDialog.setCancelable(cancelable);
        mDialog.show();
    }

    public void hideDialog() {
        if (mDialog.isShowing()) {
            mDialog.hide();//只会隐藏dialog,不会释放dialog资源
        }
    }

    public void save(User user) {
        mPref.edit().putString("username", user.getUsername()).putString("password", user.getPassword()).commit();
    }

}
