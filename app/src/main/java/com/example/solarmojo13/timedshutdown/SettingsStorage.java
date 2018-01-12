package com.example.solarmojo13.timedshutdown;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class SettingsStorage {
    //may also need to add color/theme
    public static final int ID = 01;
    public static boolean timeLayout=false;
    public static int minute=0;
    public static int hour=0;
    public static int getMinutes(){
        Calendar temp = Calendar.getInstance();
        int tHours = temp.get(Calendar.HOUR);
        int tMinutes = temp.get(Calendar.MINUTE);
        int tMinutes1 = (hour*60) + minute;
        int tMinutes2 = (tHours*60) + tMinutes;
        if(tMinutes1<tMinutes2){
            tMinutes1+=1440;
        }
        int totalMinutes = tMinutes1-tMinutes2;
        if(timeLayout){
            return totalMinutes%60;
        }
        else{
            return totalMinutes;
        }
    }
    public static int getHours(){
        Calendar temp = Calendar.getInstance();
        int tHours = temp.get(Calendar.HOUR);
        int tMinutes = temp.get(Calendar.MINUTE);
        int tMinutes1 = hour*60 + minute;
        int tMinutes2 = tHours*60 + tMinutes;
        if(tMinutes1<tMinutes2){
            tMinutes1+=1440;
        }
        int totalMinutes = tMinutes1-tMinutes2;
        return totalMinutes/60;
    }
}
