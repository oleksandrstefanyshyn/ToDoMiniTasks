package com.globallogic.trainee.ostefanyshyn.todominitasks;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BitmapResultReceiver.OnDownloadBitmapFinish, View.OnClickListener {

    public static final String URL = "https://cdn.teslarati.com/wp-content/uploads/2017/04/Musk-Boring-Company-equipment-SpaceX.jpg";
    public static final String RECEIVER = "receiver";

    private ImageView mImageView;
    private Button mButtonDownload;
    private ProgressBar mProgressBar;

    private BitmapStorage mBitmapStorage;
    private BitmapResultReceiver mBitmapResultReciver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        landscapeChecking();
    }

    private void hideViews() {
        mButtonDownload.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mButtonDownload.setVisibility(View.INVISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mButtonDownload.setVisibility(View.VISIBLE);
    }

    private void startDownloadService() {
        mBitmapResultReciver = new BitmapResultReceiver(new Handler(), this);
        Intent intent = new Intent(this, DownloadBitmapService.class);
        intent.putExtra(URL, URL);
        intent.putExtra(RECEIVER, mBitmapResultReciver);
        startService(intent);
    }

    private void initViews() {
        mImageView = findViewById(R.id.iv_bitmap);
        mButtonDownload = findViewById(R.id.btn_download);
        mProgressBar = findViewById(R.id.pb_downloading);
        mBitmapStorage = BitmapStorage.init();
    }

    @Override
    public void downloadingFinish(BitmapStorage storage) {
        mImageView.setImageBitmap(storage.getmBitmap());
        hideProgressBar();
        mButtonDownload.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (isOnline()) {
            startDownloadService();
            showProgressBar();
        } else {
            Toast.makeText(MainActivity.this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
        }
    }

    public void landscapeChecking() {
        if (mBitmapStorage.getmBitmap() == null) {
            mButtonDownload.setOnClickListener(this);
        } else {
            mImageView.setImageBitmap(mBitmapStorage.getmBitmap());
            hideViews();
        }
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
