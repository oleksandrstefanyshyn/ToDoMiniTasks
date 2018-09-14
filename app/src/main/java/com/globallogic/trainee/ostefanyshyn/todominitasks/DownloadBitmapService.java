package com.globallogic.trainee.ostefanyshyn.todominitasks;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

public class DownloadBitmapService extends IntentService {

    public static final int OK = 1;

    public static final String SERVICE = "DownloadBitmapService";
    public static final String BITMAP = "bitmap";

    public DownloadBitmapService() {
        super(SERVICE);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String url = intent.getStringExtra(MainActivity.URL);
        final ResultReceiver receiver = intent.getParcelableExtra(MainActivity.RECEIVER);
        Bundle bundle = new Bundle();
        Bitmap bitmap = ConnectionUtil.getBitmapFromURL(url);
        bundle.putParcelable(BITMAP, bitmap);
        receiver.send(OK, bundle);
    }
}
