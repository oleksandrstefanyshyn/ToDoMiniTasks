package com.globallogic.trainee.ostefanyshyn.todominitasks;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;

public class BitmapResultReceiver extends android.os.ResultReceiver {

    private BitmapStorage mBitmapStorage;
    private OnDownloadBitmapFinish mFinish;

    public BitmapResultReceiver(Handler handler, OnDownloadBitmapFinish mFinish) {
        super(handler);
        this.mFinish = mFinish;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if (resultCode == DownloadBitmapService.OK) {
            Bitmap bitmap = resultData.getParcelable(DownloadBitmapService.BITMAP);
            mBitmapStorage = BitmapStorage.getmInstance();
            mBitmapStorage.setmBitmap(bitmap);
            mFinish.downloadingFinish(mBitmapStorage);
        }
    }

    public interface OnDownloadBitmapFinish {
        void downloadingFinish(BitmapStorage storage);
    }
}
