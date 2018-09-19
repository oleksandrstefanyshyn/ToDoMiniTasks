package com.globallogic.trainee.ostefanyshyn.todominitasks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private CustomView mSalaryGraph;
    private EditText mEditTextSeventeenth;
    private Button mShowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        mShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float salary = Float.parseFloat(mEditTextSeventeenth.getText().toString());
                mSalaryGraph.setmSalarySize(salary);
                mSalaryGraph.invalidate();
            }
        });
    }

    private void initViews() {
        mSalaryGraph = findViewById(R.id.custom_view);
        mEditTextSeventeenth = findViewById(R.id.et_seventeenth);
        mShowButton = findViewById(R.id.btn_set);
    }
}
