package com.globallogic.trainee.ostefanyshyn.todominitasks.download;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.globallogic.trainee.ostefanyshyn.todominitasks.util.ConnectionUtils;

import java.util.concurrent.CountDownLatch;

public class BitmapRunnable implements Runnable {

    private static final String TAG = BitmapRunnable.class.getSimpleName();
    private static final String KEY_BITMAP = "KEY_BITMAP";

    private String mUrl;
    private Handler mDownloadHandler;
    private CountDownLatch mCountDownLatch;

    public BitmapRunnable(String url, Handler mDownloadHandler, CountDownLatch countDownLatch) {
        this.mUrl = url;
        this.mDownloadHandler = mDownloadHandler;
        this.mCountDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        mCountDownLatch.countDown();
        Bitmap bitmap = ConnectionUtils.getBitmapFromUrl(mUrl);
        try {
            mCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "run, bitmap downloaded");
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_BITMAP, bitmap);
        Message mMessage = mDownloadHandler.obtainMessage();
        mMessage.setData(bundle);
        mDownloadHandler.sendMessage(mMessage);
    }

    public static Bitmap getBitmap(Bundle bundle) {
        return bundle.getParcelable(KEY_BITMAP);
    }
}
