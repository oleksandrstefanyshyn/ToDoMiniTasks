package com.globallogic.trainee.ostefanyshyn.todominitasks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.globallogic.trainee.ostefanyshyn.todominitasks.download.BitmapRunnable;
import com.globallogic.trainee.ostefanyshyn.todominitasks.download.DownloadHandler;

import java.util.concurrent.CountDownLatch;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String FIRST = "First start";
    private static final String SECOND = "Second start";
    private static final String DOWNLOAD = "Bitmap downloaded";
    private static final int COUNT = 7;

    private Button mStartButton;
    private Button mCountButton;
    private TextView mFirstTextView;
    private TextView mSecondTextView;
    private ImageView mDownloadImageViewF;
    private ImageView mDownloadImageViewS;
    private DownloadHandler mDownloadHandlerF;
    private DownloadHandler mDownloadHandlerS;
    private CountDownLatch mCountDownLatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownloadThreads();
            }
        });

        mCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountDownLatch.countDown();
                Log.d(TAG, "mCountDownLatch.getCount(): " + mCountDownLatch.getCount());
                if(mCountDownLatch.getCount() == 0) {
                    showBitmap();
                }
            }
        });
    }

    private void initViews() {
        mStartButton = findViewById(R.id.b_start);
        mCountButton = findViewById(R.id.b_count);
        mFirstTextView = findViewById(R.id.tv_first);
        mSecondTextView = findViewById(R.id.tv_second);
        mDownloadImageViewF = findViewById(R.id.iv_download_f);
        mDownloadImageViewS = findViewById(R.id.iv_download_s);
        mDownloadHandlerF = new DownloadHandler();
        mDownloadHandlerS = new DownloadHandler();
        mCountDownLatch = new CountDownLatch(COUNT);
    }

    private void startDownloadThreads() {
        String urlFirst = getResources().getString(R.string.first);
        String urlSecond = getResources().getString(R.string.second);
        Thread first = new Thread(new BitmapRunnable(urlFirst, mDownloadHandlerF, mCountDownLatch));
        Thread second = new Thread(new BitmapRunnable(urlSecond, mDownloadHandlerS, mCountDownLatch));
        mFirstTextView.setText(FIRST);
        mSecondTextView.setText(SECOND);
        first.start();
        second.start();
    }

    private void showBitmap() {
        mFirstTextView.setText(DOWNLOAD);
        mSecondTextView.setText(DOWNLOAD);
        mDownloadImageViewF.setImageBitmap(mDownloadHandlerF.getDownloadBitmap());
        mDownloadImageViewS.setImageBitmap(mDownloadHandlerS.getDownloadBitmap());
    }
}

