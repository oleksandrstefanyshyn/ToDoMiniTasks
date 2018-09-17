package com.globallogic.trainee.ostefanyshyn.todominitasks;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TRANSLATION = "translationX";
    public static final String SCALE = "scaleX";
    public static final String ROTATION = "rotation";
    public static final long DURATION = 1000;
    public static final float SCALE_STATE = 1f;
    public static final float SCALE_MAX = 1.5f;
    public static final float SCALE_MIN = 0.5f;
    public static final float ROTATE_MIN = 0f;
    public static final float ROTATE_MAX = 360f;
    public static final float TRANSL= 800f;

    private TextView mTextViewAnimation;
    private Button mButtonMove;
    private Button mButtonScaleIn;
    private Button mButtonScaleOut;
    private Button mButtonRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        moveAnimation();
        zoomInAnimation();
        zoomOutAnimation();
        rotateAnimation();
    }

    private void initViews() {
        mTextViewAnimation = findViewById(R.id.tv_animation);
        mButtonMove = findViewById(R.id.btn_move);
        mButtonScaleIn = findViewById(R.id.btn_scale_in);
        mButtonScaleOut = findViewById(R.id.btn_scale_out);
        mButtonRotate = findViewById(R.id.btn_rotate);
    }

    private void moveAnimation() {
        mButtonMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(mTextViewAnimation, TRANSLATION, -TRANSL, TRANSL)
                        .setDuration(DURATION).start();
            }
        });
    }

    private void zoomInAnimation() {
        mButtonScaleIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(mTextViewAnimation, SCALE, SCALE_STATE, SCALE_MAX)
                        .setDuration(DURATION).start();
            }
        });
    }

    private void zoomOutAnimation() {
        mButtonScaleOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(mTextViewAnimation, SCALE, SCALE_STATE, SCALE_MIN)
                        .setDuration(DURATION).start();
            }
        });
    }

    private void rotateAnimation() {
        mButtonRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(mTextViewAnimation, ROTATION, ROTATE_MIN, ROTATE_MAX)
                        .setDuration(DURATION).start();
            }
        });
    }
}
