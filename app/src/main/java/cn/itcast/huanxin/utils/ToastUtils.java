package cn.itcast.huanxin.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by Kevin.
 */
public class ToastUtils {

    //初始化主线程Handler
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    private static Toast mToast;

    public static void showToast(Context ctx, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
        }

        mToast.setText(msg);

        if (Looper.myLooper() == Looper.getMainLooper()) {//主线程
            mToast.show();
        } else {
            //子线程
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mToast.show();
                }
            });
        }
    }
}
