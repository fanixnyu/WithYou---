package com.atguigu.staservice.schedule;
import com.atguigu.staservice.service.StatisticsDailyService;
import com.atguigu.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component//交个spring管理
public class ScheduledTask {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

//    每个5秒执行一次
    @Scheduled(cron = "0/5 * * * * ?")
    public void task1(){
        System.out.println("----------执行了-----------");
    }



//    在每天凌晨1点，把前一天数据进行数据查询添加
    @Scheduled(cron = "0 0 1 * * ?")
    public void task2(){
        statisticsDailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(),-1)));
    }
}
