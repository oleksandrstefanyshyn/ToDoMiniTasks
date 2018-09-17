package com.globallogic.trainee.ostefanyshyn.todominitasks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final long DURATION = 1000;
    public static final float ALPHA = 0.5f;
    public static final float SCALE = 3f;
    public static final float ROTATE = 360f;
    public static final float TRANSL= 300f;

    private TextView mTextViewAnimation;
    private Button mButtonRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        rotateAnimation();
    }

    private void initViews() {
        mTextViewAnimation = findViewById(R.id.tv_animation);
        mButtonRotate = findViewById(R.id.btn_rotate);
    }

    private void rotateAnimation() {
        mButtonRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextViewAnimation.animate()
                        .scaleX(SCALE)
                        .rotation(ROTATE)
                        .translationX(TRANSL)
                        .alpha(ALPHA)
                        .setDuration(DURATION);
            }
        });
    }
}
