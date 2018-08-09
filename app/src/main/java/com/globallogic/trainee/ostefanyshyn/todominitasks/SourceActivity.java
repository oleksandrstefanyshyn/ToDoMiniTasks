package com.globallogic.trainee.ostefanyshyn.todominitasks;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SourceActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_1 = 1;

    private EditText mEnterDataEditText;
    private Button mPassDataNextActivityButton;
    private Button mPassDataGetResultButton;
    private TextView mInfoTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.source_activity);

       mEnterDataEditText = findViewById(R.id.enter_data_edit_text);
       mPassDataNextActivityButton = findViewById(R.id.pass_data_next_activity_button);
       mPassDataGetResultButton = findViewById(R.id.pass_data_get_result_button);
       mInfoTextView = findViewById(R.id.info_text_view);

       mPassDataNextActivityButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(SourceActivity.this, TargetActivity.class);
               intent.putExtra("msg", mEnterDataEditText.getText().toString());
               startActivity(intent);
           }
       });

       mPassDataGetResultButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(SourceActivity.this, TargetActivity.class);
               intent.putExtra("msg", mEnterDataEditText.getText().toString());
               startActivityForResult(intent, REQUEST_CODE_1);
           }
       });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_1:
                if (resultCode == RESULT_OK) {
                    String messageReturn = data.getStringExtra("return_msg");
                    Toast.makeText(getApplicationContext(), messageReturn, Toast.LENGTH_LONG).show();
                }
        }
    }
}
