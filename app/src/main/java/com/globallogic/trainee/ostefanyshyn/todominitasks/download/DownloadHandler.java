package com.globallogic.trainee.ostefanyshyn.todominitasks.download;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class DownloadHandler extends Handler {

    private static String TAG = DownloadHandler.class.getSimpleName();

    private ImageView mTargetImageView;

    public DownloadHandler(ImageView mTargetImageView) {
        this.mTargetImageView = mTargetImageView;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Bitmap bitmap = BitmapRunnable.getBitmap(msg.getData());
        mTargetImageView.setImageBitmap(bitmap);
    }
}
