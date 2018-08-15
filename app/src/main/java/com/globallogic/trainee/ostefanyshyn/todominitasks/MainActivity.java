package com.globallogic.trainee.ostefanyshyn.todominitasks;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.globallogic.trainee.ostefanyshyn.todominitasks.fragment.FirstFragment;
import com.globallogic.trainee.ostefanyshyn.todominitasks.fragment.FragmenLoader;
import com.globallogic.trainee.ostefanyshyn.todominitasks.recycler.RecyclerViewActivity;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Bitmap>{

    private static final String URL = "https://tesla-group.com.ua/wp-content/themes/theme-tessla/img/model-select-modelx.png";

    private ImageLoaderManager mImageLoaderManager;

    private ImageView mMainImageView;
    private Button mFirstFragmentButton;
    private Button mSecondFragmentButton;
    private Button mRecyclerActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageLoaderManager = new ImageLoaderManager();

        mMainImageView = findViewById(R.id.iv_main);
        mRecyclerActivityButton = findViewById(R.id.b_call_recycler_activity);
        mFirstFragmentButton = findViewById(R.id.b_call_first_fragment);
        mSecondFragmentButton = findViewById(R.id.b_call_second_fragment);

        makeOperationDownloadImage(getSupportLoaderManager());

        mRecyclerActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
            }
        });

        mFirstFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmenLoader.loadFragment(new FirstFragment(), getSupportFragmentManager());
            }
        });

        mSecondFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmenLoader.loadFragment(new SecondFragment(), getSupportFragmentManager());
            }
        });
    }

    @NonNull
    @Override
    public Loader onCreateLoader(int i, @Nullable Bundle bundle) {
        return new AsyncTaskLoader<Bitmap>(this) {
            @Nullable
            @Override
            public Bitmap loadInBackground() {
                return mImageLoaderManager.loadBitmap(URL);
            }

            @Override
            protected void onStartLoading() {
                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Bitmap> loader, Bitmap bitmap) {
        mImageLoaderManager.setBitmapToImageView(mMainImageView, bitmap);
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {
    }

    private void makeOperationDownloadImage (LoaderManager manager) {
        manager.initLoader(0, null, this);
    }
}
