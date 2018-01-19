package com.example.solarmojo13.timedshutdown;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationPublisher extends BroadcastReceiver {

    public static String NOTIFICATION = "notification";

    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Shutdown time in future");
        String text = "";
        if(SettingsStorage.timeLayout){
            text = "Shutdown the phone in " + SettingsStorage.getHours() + " hours and " + SettingsStorage.getMinutes() + " minutes";
        }
        else{
            text = "Shutdown the phone in " + SettingsStorage.getMinutes() + " minutes";
        }
        builder.setContentText(text);
        builder.setSmallIcon(R.drawable.notification);
        notificationManager.notify(SettingsStorage.ID, builder.build());

    }
}
