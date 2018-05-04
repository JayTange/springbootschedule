## 定时线程
说到定时任务，通常会想到JDK自带的定时线程来执行，定时任务。
回顾一下定时线程池。

```
public static ScheduledExecutorService newScheduledThreadPool(int var0) {
        return new ScheduledThreadPoolExecutor(var0);
    }

    public static ScheduledExecutorService newScheduledThreadPool(int var0, ThreadFactory var1) {
        return new ScheduledThreadPoolExecutor(var0, var1);
    }
```  
常用的两个方法：
scheduleAtFixedRate:是以固定的频率去执行任务，周期是指每次执行任务成功执行之间的间隔。

schedultWithFixedDelay:是以固定的延时去执行任务，延时是指上一次执行成功之后和下一次开始执行的之前的时间。

看一个DEMO：
```
public class ScheduledExecutorServiceDemo {
    public static void main(String args[]) {

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    System.out.println(Thread.currentThread().getId() + "执行了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2, TimeUnit.SECONDS);
    }
}
```  
具体细节我就不再赘述了，有兴趣的可以查看我关于线程池的博客：[链接](http://www.cnblogs.com/superfj/p/7544971.html)


## springboot的定时任务
### pom的依赖：

```
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>
```  
### 启动类启用定时
```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StartApplication {
    public static void main(String args[]){
        SpringApplication application = new SpringApplication(StartApplication.class);
        application.run(args);
    }
}
```

### 定时任务业务类：
```
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

    /**
     * 以固定的频率去执行任务
     */
    @Scheduled(initialDelay = 10000,fixedRate = 3000)
    public void  reportNumber(){
        System.out.println(count.incrementAndGet());
    }

    /**
     * 以固定的延时去执行任务
     */
    @Scheduled(initialDelay = 10000,fixedDelay = 3000)
    public void  reportNumberDelay(){
        System.out.println(count.incrementAndGet());
    }
}

```  
运行结果如下：
```
现在的时间是：09:59:57
1
2
现在的时间是：10:00:03
3
4
5
6
现在的时间是：10:00:09
7
```  
### 使用说明：
- @Scheduled(fixedRate = 1000) ：上一次开始执行时间点之后1秒再执行
- @Scheduled(fixedDelay = 1000) ：上一次执行完毕时间点之后1秒再执行
- @Scheduled(initialDelay=1000, fixedRate=6000) ：第一次延迟1秒后执行，之后按fixedRate的规则每6秒执行一次
@Scheduled(initialDelay=1000, fixedDelay=6000) ：第一次延迟1秒后执行，之后按fixedDelay的规则每6秒执行一次

**[源码地址](https://github.com/JayTange/springbootschedule)**
