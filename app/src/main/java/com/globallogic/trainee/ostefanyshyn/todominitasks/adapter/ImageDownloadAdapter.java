package com.globallogic.trainee.ostefanyshyn.todominitasks.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.globallogic.trainee.ostefanyshyn.todominitasks.download.BitmapRunnable;
import com.globallogic.trainee.ostefanyshyn.todominitasks.download.DownloadHandler;
import com.globallogic.trainee.ostefanyshyn.todominitasks.R;

import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class ImageDownloadAdapter extends BaseAdapter {

    private static final int COUNT = 10;

    private int mSemaphoreSize = COUNT;
    private int mSize = COUNT;
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(mSize);
    private Semaphore mSemaphore = new Semaphore(mSemaphoreSize, true);
    private List<String> listUrl;

    public ImageDownloadAdapter(List<String> listUrl) {
        this.listUrl = listUrl;
    }

    @Override
    public int getCount() {
        return listUrl.size();
    }

    @Override
    public String getItem(int position) {
        return listUrl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        ImageView itemImageView = view.findViewById(R.id.iv_item);
        String url = listUrl.get(position);
        downloadBitmap(itemImageView, url);
        return view;
    }

    public void setmSize(int mSize) {
        this.mSize = mSize;
        notifyDataSetChanged();
        cyclicBarrier = new CyclicBarrier(mSize);
    }

    public void setmSemaphoreSize(int mSemaphoreSize) {
        this.mSemaphoreSize = mSemaphoreSize;
        notifyDataSetChanged();
        mSemaphore = new Semaphore(mSemaphoreSize);
    }

    private void downloadBitmap(ImageView imageView, String url) {
        DownloadHandler downloadHandler = new DownloadHandler(imageView);
        Thread thread = new Thread(new BitmapRunnable(url, downloadHandler, cyclicBarrier, mSemaphore));
        thread.start();
    }
}
