package cn.itcast.huanxin.presenter.impl;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.itcast.huanxin.model.User;
import cn.itcast.huanxin.presenter.RegisterPresenter;
import cn.itcast.huanxin.utils.ThreadUtils;
import cn.itcast.huanxin.view.inter.RegisterView;

/**
 * Created by Kevin.
 */
public class RegisterPresenterImpl implements RegisterPresenter {

    public RegisterView mView;

    public RegisterPresenterImpl(RegisterView view) {
        mView = view;
    }

    @Override
    public void startRegister(final String username, final String password) {

        mView.showProgressDialog("正在注册,请稍候...");

        final User user = new User(username, password);

        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User u, BmobException e) {
                if (e == null) {//云服务器添加成功
                    ThreadUtils.runOnSubThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                EMClient.getInstance().createAccount(username, password);
                                ThreadUtils.runOnMainThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mView.onRegisterFinish(user, true, null);
                                        mView.hideProgressDialog();
                                    }
                                });
                            } catch (final HyphenateException e1) {
                                e1.printStackTrace();
                                user.delete();

                                ThreadUtils.runOnMainThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mView.onRegisterFinish(user, false, e1.getMessage());
                                        mView.hideProgressDialog();
                                    }
                                });
                            }
                        }
                    });
                } else {//云服务器添加失败
                    mView.onRegisterFinish(user, false, e.getMessage());
                    mView.hideProgressDialog();
                }
            }
        });
    }
}
