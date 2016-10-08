package cn.itcast.huanxin.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kevin.
 */
public class ThreadUtils {

    public static ExecutorService mPool = Executors.newSingleThreadExecutor();

    public static Handler mHandler = new Handler(Looper.getMainLooper());

    //运行在子线程
    public static void runOnSubThread(Runnable r) {
        mPool.execute(r);
    }

    //运行在主线程
    public static void runOnMainThread(Runnable r) {
        mHandler.post(r);
    }
}
