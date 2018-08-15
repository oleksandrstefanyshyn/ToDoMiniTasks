package com.globallogic.trainee.ostefanyshyn.todominitasks;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.globallogic.trainee.ostefanyshyn.todominitasks.util.ConnectionUtils;

public class ImageLoaderManager {

    private static int MB = 1024;
    private static int MEMORY_DIVIDER = 8;

    private static TCLruCache mTcLruCache;

    ImageLoaderManager() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / MB);
        final int cacheSize = maxMemory / MEMORY_DIVIDER;
        mTcLruCache = new TCLruCache(cacheSize);
    }

    private void putBitMapToMemoryCash(Bitmap bitmap, String url) {
            mTcLruCache.put(url, bitmap);
    }

    private Bitmap getBitmapFromCash(String url) {
        return mTcLruCache.get(url);
    }

    Bitmap loadBitmap(String url) {
        Bitmap bitmap = ConnectionUtils.getBitmapFromURL(url);
        putBitMapToMemoryCash(bitmap, url);
        return bitmap;
    }

    void setBitmapToImageView(ImageView imageView, Bitmap bitmap) {
       // Bitmap bitmap = getBitmapFromCash(url);
        imageView.setImageBitmap(bitmap);
    }

    private class TCLruCache extends LruCache<String, Bitmap> {

        TCLruCache(int maxSize) {
            super(maxSize);
        }

        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getByteCount() / 1024;
        }
    }
}
