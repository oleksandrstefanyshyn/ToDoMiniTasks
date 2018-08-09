package com.globallogic.trainee.ostefanyshyn.todominitasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TargetActivity extends AppCompatActivity {

    private static final String RETURN_MESSAGE = "This data is returned when user click " +
                                                      "button in target activity.";

    private Button mPassBackDataButton;
    private TextView mRequestDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.target_activity);

        mPassBackDataButton = findViewById(R.id.pass_back_data_button);
        mRequestDataTextView = findViewById(R.id.request_data_text_view);

        Intent intentMessage = getIntent();
        String  massage = intentMessage.getStringExtra("msg");
        mRequestDataTextView.setText(massage);

        mPassBackDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TargetActivity.this, SourceActivity.class);
                intent.putExtra("return_msg", RETURN_MESSAGE);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
