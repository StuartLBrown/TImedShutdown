package com.example.solarmojo13.timedshutdown;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.PowerManager;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.io.Console;
import java.sql.Time;

public class MainActivity extends AppCompatActivity {
    private int finalHour;
    private int finalMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reset();
        final TimePicker timePicker = (TimePicker) findViewById(R.id.timerShutdown);
        final Button btnConfirm = (Button) findViewById(R.id.btnConfirm);
        final ImageButton imgbtnSetting = (ImageButton) findViewById(R.id.imgbtnSetting);
        SettingsStorage.hour= timePicker.getCurrentHour();
        SettingsStorage.minute = timePicker.getCurrentMinute();
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int _minute) {
                if (timePicker.isEnabled()) {
                    SettingsStorage.hour = timePicker.getCurrentHour();
                    SettingsStorage.minute = timePicker.getCurrentMinute();
                    btnConfirm.setEnabled(true);
                }
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void goToSettings(View view){
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }
    public void confirm(View view){
        final TimePicker timePicker = (TimePicker) findViewById(R.id.timerShutdown);
        final Button btnConfirm = (Button) findViewById(R.id.btnConfirm);
        finalHour = SettingsStorage.hour;
        finalMinute = SettingsStorage.minute;
        timePicker.setEnabled(false);
        btnConfirm.setEnabled(false);
        String CHANNEL_ID = "01";
        android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Shutdown time set")
                .setContentText("Shutdown in " + SettingsStorage.minutes + " minutes");
        builder.build();
    }
    private void reset(){
        final TimePicker tp = (TimePicker) findViewById(R.id.timerShutdown);
        final Button btn = (Button) findViewById(R.id.btnConfirm);
        tp.setEnabled(true);
        btn.setEnabled(false);
    }
}
