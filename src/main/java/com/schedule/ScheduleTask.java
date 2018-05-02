package com.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ScheduleTask {

    private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    private AtomicInteger count = new AtomicInteger();

    @Scheduled(fixedRate = 6000)
    public void  reportTime(){
        System.out.println("现在的时间是："+format.format(new Date()));
    }

    @Scheduled(initialDelay = 10000,fixedRate = 3000)
    public void  reportNumber(){
        System.out.println(count.incrementAndGet());
    }

    @Scheduled(initialDelay = 10000,fixedDelay = 3000)
    public void  reportNumberDelay(){
        System.out.println(count.incrementAndGet());
    }
}
