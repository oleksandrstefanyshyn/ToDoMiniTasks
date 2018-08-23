package com.globallogic.trainee.ostefanyshyn.todominitasks.download;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.globallogic.trainee.ostefanyshyn.todominitasks.util.ConnectionUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class BitmapRunnable implements Runnable {

    private static final String TAG = BitmapRunnable.class.getSimpleName();
    private static final String KEY_BITMAP = "KEY_BITMAP";

    private String mUrl;
    private Handler mDownloadHandler;
    private CyclicBarrier cyclicBarrier;
    private Semaphore semaphore;

    public BitmapRunnable(String mUrl, Handler mDownloadHandler, CyclicBarrier cyclicBarrier, Semaphore semaphore) {
        this.mUrl = mUrl;
        this.mDownloadHandler = mDownloadHandler;
        this.cyclicBarrier = cyclicBarrier;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            cyclicBarrier.await();
            Log.d(TAG, "run, await()");
            semaphore.acquire();
            Log.d(TAG, "run, acquire()");
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
        Log.d(TAG, "run, before semaphore.release()");
        semaphore.release();
        Log.d(TAG, "run, after semaphore.release()");
    }

    public static Bitmap getBitmap(Bundle bundle) {
        return bundle.getParcelable(KEY_BITMAP);
    }
}
