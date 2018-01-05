package com.example.solarmojo13.timedshutdown;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class SettingsStorage {
    //may also need to add color/theme
    public static boolean timeLayout=false;
    public static int minute=0;
    public static int hour=0;
    public static int minutes = getMinutes();
    public static int hours = getHours();
    private static int getMinutes(){
        if(timeLayout){
            Date temp = Calendar.getInstance().getTime();
            return (hour);
        }
        else{
            Date temp = Calendar.getInstance().getTime();
            return Math.abs(temp.getMinutes()-minute);
        }
    }
    private static int getHours(){
        return Calendar.getInstance().getTime().getHours()-hour;
    }
}
