package com.globallogic.trainee.ostefanyshyn.todominitasks;

import android.graphics.Bitmap;

public class BitmapStorage {

    private static BitmapStorage mInstance;
    private Bitmap mBitmap;

    private BitmapStorage() {
        mBitmap = null;
    }

    public static BitmapStorage init() {
        if (mInstance == null) {
            mInstance = new BitmapStorage();
        }
        return mInstance;
    }

    public static BitmapStorage getmInstance() {
        return mInstance;
    }

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }
}
