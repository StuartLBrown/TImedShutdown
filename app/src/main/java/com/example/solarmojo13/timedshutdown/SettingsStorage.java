package com.example.solarmojo13.timedshutdown;

import java.sql.Time;
import java.util.Calendar;

public class SettingsStorage {
    //may also need to add color/theme
    //may want to add # of notifications
    //also want to add if they want notifications at all
    public static int numNotifications=0;
    public static int minute=0;
    public static int hour=0;
    public static int minutes = (Calendar.getInstance().getTime().getMinutes() + (Calendar.getInstance().getTime().getHours()*60))-((hour * 60)+minute);
}
