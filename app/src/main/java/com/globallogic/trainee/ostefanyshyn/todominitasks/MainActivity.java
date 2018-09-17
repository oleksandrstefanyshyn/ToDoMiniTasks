package com.globallogic.trainee.ostefanyshyn.todominitasks;

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

    private TextView mTextViewAnimation;
    private Button mButtonMove;
    private Button mButtonScaleIn;
    private Button mButtonScaleOut;
    private Button mButtonRotate;

    private Animation mAnimMove;
    private Animation mAnimScaleIn;
    private Animation mAnimScaleOut;
    private Animation mAnimRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        loadAnimations();
        moveAnimation();
        zoomInAnimation();
        zoomOutAnimation();
        rotateAnimation();
    }

    private void loadAnimations() {
        mAnimMove = AnimationUtils.loadAnimation(this, R.anim.move);
        mAnimScaleIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        mAnimScaleOut = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        mAnimRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
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
                mTextViewAnimation.startAnimation(mAnimMove);
            }
        });
    }

    private void zoomInAnimation() {
        mButtonScaleIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextViewAnimation.startAnimation(mAnimScaleIn);
            }
        });
    }

    private void zoomOutAnimation() {
        mButtonScaleOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextViewAnimation.startAnimation(mAnimScaleOut);
            }
        });
    }

    private void rotateAnimation() {
        mButtonRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextViewAnimation.startAnimation(mAnimRotate);
            }
        });
    }
}
