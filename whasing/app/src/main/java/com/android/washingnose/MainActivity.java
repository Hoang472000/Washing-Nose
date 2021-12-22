package com.android.washingnose;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mNameApp, mTextTermsPrivacy;
    private ImageView mImageDevice;
    private CheckBox mTermsPrivacy;
    private Button mStart;
    private NoseSharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadInit();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        init();
        mTermsPrivacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPreferences.setChecked(isChecked);
            }
        });
        mTextTermsPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "This is Link Web", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    if (mTermsPrivacy.isChecked()) {
                        Intent intent = new Intent(MainActivity.this, ListDevice.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, R.string.toast_terms_privacy, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void loadInit() {
        sharedPreferences = new NoseSharedPreferences(this);
        if (sharedPreferences != null) {
            boolean TermsPrivacy = sharedPreferences.getchecked();
            if (TermsPrivacy) {
                Intent intent = new Intent(MainActivity.this, ListDevice.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        }
    }

    private void init() {
        mNameApp = findViewById(R.id.name_app);
        mImageDevice = findViewById(R.id.image_device);
        mTermsPrivacy = findViewById(R.id.terms_privacy);
        mStart = findViewById(R.id.start);
        mTextTermsPrivacy = findViewById(R.id.text_terms_privacy);
    }

}