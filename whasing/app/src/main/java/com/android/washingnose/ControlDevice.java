package com.android.washingnose;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ControlDevice extends AppCompatActivity {

    private Switch mSchedule;
    private TextView mTime;
    private List<String> list = new ArrayList<>();
    private NoseSharedPreferences sharedPreferences;
    private String mAddress;
    private final int NUMBER_VALUE_LIST_ITEM = 12;
    private String[] arr = new String[NUMBER_VALUE_LIST_ITEM];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_device);

        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("address") != null) {
            mAddress = bundle.getString("address");
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        init();
        initData();

        mSchedule.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    showDialogNumberPicker();
                } else {
                    mTime.setVisibility(View.GONE);
                }
                ;
            }
        });
    }

    void init() {
        mSchedule = findViewById(R.id.schedule_Reminders);
        mTime = findViewById(R.id.time);
    }
    void initData(){
        for (int i = 0; i < NUMBER_VALUE_LIST_ITEM; i++) {
            if (i < 2) {
                arr[i] = "0" + i * 5;
            } else {
                arr[i] = String.valueOf(i * 5);
            }
        }
        sharedPreferences = new NoseSharedPreferences(getApplicationContext());
        if (sharedPreferences != null) {
            mSchedule.setChecked(sharedPreferences.getSchedule(mAddress));
            if (mSchedule.isChecked()) {
                mTime.setVisibility(View.VISIBLE);
                mTime.setText(sharedPreferences.getTime(mAddress));
            }
        }
    }
public void showDialogNumberPicker(){
    final AlertDialog.Builder builder = new AlertDialog.Builder(ControlDevice.this);
    LayoutInflater inflater = this.getLayoutInflater();
    View dialogView = inflater.inflate(R.layout.dialog_timer, null);
    builder.setTitle(R.string.add_time);
    builder.setView(dialogView);
    final NumberPicker numberPickerTime = (NumberPicker) dialogView.findViewById(R.id.numberPickerTime);
    numberPickerTime.setMaxValue(12);
    numberPickerTime.setMinValue(1);
    numberPickerTime.setWrapSelectorWheel(false);
    numberPickerTime.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker numberPicker, int i, int i1) {

        }
    });

    final NumberPicker numberPicker = (NumberPicker) dialogView.findViewById(R.id.numberPicker);
    numberPicker.setMaxValue(12);
    numberPicker.setMinValue(1);
    numberPicker.setDisplayedValues(arr);
    numberPicker.setWrapSelectorWheel(false);
    numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker numberPicker, int i, int i1) {

        }
    });
    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            String time =String.valueOf(numberPickerTime.getValue())+":"+arr[numberPicker.getValue()-1];
            mTime.setText(time);
            mTime.setVisibility(View.VISIBLE);
            sharedPreferences.setTime(time, mAddress);
            sharedPreferences.setSchedule(true, mAddress);
        }
    });
    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            mTime.setVisibility(View.GONE);
            mSchedule.setChecked(false);
            sharedPreferences.setSchedule(false, mAddress);
        }
    });
    AlertDialog alertDialog = builder.create();
    alertDialog.show();
}

    public void updateTime(String addTime){
        String str = String.valueOf(mTime.getText());
        String[] time = str.split(":");
        int hour =Integer.valueOf(time[0]) +Integer.valueOf(addTime);
        mTime.setText(String.valueOf(hour)+":"+time[1]);

    }
}