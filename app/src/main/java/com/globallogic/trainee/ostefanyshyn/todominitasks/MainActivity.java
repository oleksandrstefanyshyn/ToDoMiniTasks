package com.globallogic.trainee.ostefanyshyn.todominitasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String MYPREFERENCE = "myPref";
    public static final String NAME = "nameKey";
    public static final String EMAIL = "emailKey";

    private EditText mNameEditText;
    private EditText mEmailEditText;

    private Button mSaveButton;
    private Button mRetrieveButton;
    private Button mClearButton;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initSharedPreferences();
        setListenerOnButtons();
    }

    private void initSharedPreferences() {
        mSharedPreferences = getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        containsString(mNameEditText, NAME);
        containsString(mEmailEditText, EMAIL);
    }

    private void containsString(EditText view, String str) {
        if(mSharedPreferences.contains(str)) {
            view.setText(mSharedPreferences.getString(str, ""));
        }
    }

    private void initViews() {
        mNameEditText = findViewById(R.id.etName);
        mEmailEditText = findViewById(R.id.etEmail);
        mSaveButton = findViewById(R.id.btnSave);
        mRetrieveButton = findViewById(R.id.btnRetr);
        mClearButton = findViewById(R.id.btnClear);
    }

    private void saveText() {
        String name = mNameEditText.getText().toString();
        String email = mEmailEditText.getText().toString();
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(NAME, name);
        editor.putString(EMAIL, email);
        editor.apply();
    }

    private void retrieveText() {
        initSharedPreferences();
    }

    private void clearText() {
        mEmailEditText.getText().clear();
        mNameEditText.getText().clear();
    }

    private void setListenerOnButtons() {
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveText();
            }
        });

        mRetrieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveText();
            }
        });

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
            }
        });
    }
}
