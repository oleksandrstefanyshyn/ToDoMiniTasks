package com.globallogic.trainee.ostefanyshyn.todominitasks.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.globallogic.trainee.ostefanyshyn.fragmentclicker.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    private final ImageView mRecyclerImageView;
    private final TextView mRecyclerTextView;


    public ViewHolder(View itemView) {
        super(itemView);
        mRecyclerImageView = itemView.findViewById(R.id.iv_recycler);
        mRecyclerTextView = itemView.findViewById(R.id.tv_recycler);
    }
}
