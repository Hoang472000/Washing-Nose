package com.android.washingnose;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class ControlDevice extends AppCompatActivity {

    private Switch mSchedule;
    private TextView mTime;
    private NoseSharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_device);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        init();
        sharedPreferences = new NoseSharedPreferences(getApplicationContext());
        if(sharedPreferences != null){
            mTime.setText(sharedPreferences.getTime());
        }
        mSchedule.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    final Calendar c = Calendar.getInstance();
                    int mHour = c.get(Calendar.HOUR_OF_DAY);
                    int mMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(ControlDevice.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {

                                    mTime.setText(hourOfDay + ":" + minute);
                                    sharedPreferences.setTime(hourOfDay + ":" + minute);
                                }
                            }, mHour, mMinute, false);
                    timePickerDialog.setTitle(R.string.enter_time);
                    timePickerDialog.setButton(TimePickerDialog.BUTTON_POSITIVE, "" + R.string.ok, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // do something
                        }
                    });
                    timePickerDialog.setButton(TimePickerDialog.BUTTON_NEGATIVE, "" + R.string.cancel, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // do something
                        }
                    });
                    timePickerDialog.show();

                } else mTime.setText("");
            }
        });
    }

    void init() {
        mSchedule = findViewById(R.id.schedule_Reminders);
        mTime = findViewById(R.id.time);
    }
}