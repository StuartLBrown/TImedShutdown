package com.example.solarmojo13.timedshutdown;

import android.content.Intent;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.io.Console;

public class MainActivity extends AppCompatActivity {
    private int hour;
    private int minute;
    private int finalHour;
    private int finalMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TimePicker timePicker = (TimePicker) findViewById(R.id.timerShutdown);
        final Button btnConfirm = (Button) findViewById(R.id.btnConfirm);
        final ImageButton imgbtnSetting = (ImageButton) findViewById(R.id.imgbtnSetting);
        hour = timePicker.getCurrentHour();
        minute = timePicker.getCurrentMinute();
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int _minute) {
                if (timePicker.isEnabled()) {
                    hour = timePicker.getCurrentHour();
                    minute = timePicker.getCurrentMinute();
                    btnConfirm.setEnabled(true);
                }
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalHour = hour;
                finalMinute = minute;
                timePicker.setEnabled(false);
                btnConfirm.setEnabled(false);
            }
        });
    }
    public void goToSettings(View view){
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }
}
