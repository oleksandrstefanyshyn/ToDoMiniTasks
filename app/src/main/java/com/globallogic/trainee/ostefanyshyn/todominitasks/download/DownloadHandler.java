package com.globallogic.trainee.ostefanyshyn.todominitasks.download;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class DownloadHandler extends Handler {

    private static String TAG = DownloadHandler.class.getSimpleName();

    private Bitmap mBitmap;

    public DownloadHandler() {
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        mBitmap = BitmapRunnable.getBitmap(msg.getData());
    }

    public Bitmap getDownloadBitmap() {
        return mBitmap;
    }
}
