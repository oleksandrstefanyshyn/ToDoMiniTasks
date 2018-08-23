package com.globallogic.trainee.ostefanyshyn.todominitasks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.globallogic.trainee.ostefanyshyn.todominitasks.adapter.ImageDownloadAdapter;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    private static int count = 0;

    private Button mDownloadButton;
    private EditText mDownloadEditText;
    private EditText mSemaphoreditText;
    private ListView mListView;
    private ImageDownloadAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        List<String> mUrls = Arrays.asList(getResources().getStringArray(R.array.images_urls));
        mAdapter = new ImageDownloadAdapter(mUrls);
        mListView.setAdapter(mAdapter);

        mDownloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int editedNumberParties = Integer.valueOf(mDownloadEditText.getText().toString());
                int editedNumberSemaphore = Integer.valueOf(mDownloadEditText.getText().toString());
                count++;
                Log.d(TAG, "Count: " + count);
                if (count == editedNumberParties) {
                    mAdapter.setmSize(editedNumberParties);
                }
                if (count == editedNumberSemaphore) {
                    mAdapter.setmSemaphoreSize(editedNumberSemaphore);
                }
            }
        });
    }

    private void initViews() {
        mDownloadButton = findViewById(R.id.b_main);
        mDownloadEditText = findViewById(R.id.ed_parties);
        mListView = findViewById(R.id.list_view);
        mSemaphoreditText = findViewById(R.id.ed_semaphore);
    }
}

