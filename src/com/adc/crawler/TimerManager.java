package com.adc.crawler;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * @author zhichengliu
 * @create -02-27-11:33
 **/

public class TimerManager {
    public static void main(String[] args) {
        new TimerManager();
    }
    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
    public TimerManager() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date=calendar.getTime();
        Timer timer = new Timer();
        Crawler task = new Crawler();
        timer.schedule(task, date, PERIOD_DAY);
    }
}
