package com.globallogic.trainee.ostefanyshyn.todominitasks.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionUtils {

    public static Bitmap getBitmapFromURL(String src) {
        Log.d("TAG", "ConnectionUtils.get");
        try {
            if (src == null && src.equals("")) {
                return null;
            }
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
            return myBitmap;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
