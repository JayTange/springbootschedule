package com.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduleTask {

    private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 6000)
    public void  reportTime(){
        System.out.println("现在的时间是："+format.format(new Date()));
    }

}
