package com.globallogic.trainee.ostefanyshyn.todominitasks.download;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.globallogic.trainee.ostefanyshyn.todominitasks.util.ConnectionUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BitmapRunnable implements Runnable {

    private static final String TAG = BitmapRunnable.class.getSimpleName();
    private static final String KEY_BITMAP = "KEY_BITMAP";

    private String mUrl;
    private Handler mDownloadHandler;
    private CyclicBarrier cyclicBarrier;

    public BitmapRunnable(String mUrl, Handler mDownloadHandler, CyclicBarrier cyclicBarrier) {
        this.mUrl = mUrl;
        this.mDownloadHandler = mDownloadHandler;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            cyclicBarrier.await();
            Log.d(TAG, "run, await");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        Bitmap bitmap = ConnectionUtils.getBitmapFromUrl(mUrl);
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
