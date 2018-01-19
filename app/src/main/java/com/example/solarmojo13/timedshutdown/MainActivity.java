package com.example.solarmojo13.timedshutdown;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTheme(R.style.Theme_AppCompat_Light);
        reset();
        final TimePicker timePicker = (TimePicker) findViewById(R.id.timerShutdown);
        final Button btnConfirm = (Button) findViewById(R.id.btnConfirm);
        final ImageButton imgbtnSetting = (ImageButton) findViewById(R.id.imgbtnSetting);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int _minute) {
                if (timePicker.isEnabled()) {
                    btnConfirm.setEnabled(true);
                }
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsStorage.hour = timePicker.getCurrentHour();
                SettingsStorage.minute = timePicker.getCurrentMinute();
                timePicker.setEnabled(false);
                btnConfirm.setEnabled(false);
                setNotification();
                String text = "";
                if(SettingsStorage.timeLayout){
                    text = "Shutdown the phone in " + SettingsStorage.getHours() + " hours and " + SettingsStorage.getMinutes() + " minutes";
                }
                else{
                    text = "Shutdown the phone in " + SettingsStorage.getMinutes() + " minutes";
                }
                scheduleNotification(getNotification(text,"Shutdown time in future"),SettingsStorage.delay);
            }
        });
    }
    public void goToSettings(View view){
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }

    public void setNotification(){
        String text = "";
        if(SettingsStorage.timeLayout){
            text = "Shutdown the phone in " + SettingsStorage.getHours() + " hours and " + SettingsStorage.getMinutes() + " minutes";
        }
        else{
            text = "Shutdown the phone in " + SettingsStorage.getMinutes() + " minutes";
        }
        Notification.Builder builder = new Notification.Builder(this).setContentTitle("Shutdown time set").setContentText(text)
                .setSmallIcon(R.drawable.notification);
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(SettingsStorage.ID,builder.build());
    }
    private void scheduleNotification(Notification notification, long delay) {
        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra("" + SettingsStorage.ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        if(SettingsStorage.getMinutes()<=0&&SettingsStorage.getHours()<=0)
            alarmManager.cancel(pendingIntent);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,20000,delay,pendingIntent);
        //alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content,String title) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.notification);
        return builder.build();
    }
    private void reset(){
        final TimePicker tp = (TimePicker) findViewById(R.id.timerShutdown);
        final Button btn = (Button) findViewById(R.id.btnConfirm);
        tp.setEnabled(true);
        btn.setEnabled(false);
    }
}
