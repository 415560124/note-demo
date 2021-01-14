package com.rhy.note.time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author Rhy
 * @title 时间差示例
 * @description 时间差示例
 * @createTime 2021年01月14日 16:30:00
 * @modifier：Rhy
 * @modification_time：2021-01-14 16:30
 */
public class TimeDifference {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.parse("2020-01-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("计算两个时间的差：");
        LocalDateTime end = LocalDateTime.parse("2020-01-02 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Duration duration = Duration.between(now,end);
        long days = duration.toDays(); //相差的天数
        long hours = duration.toHours();//相差的小时数
        long minutes = duration.toMinutes();//相差的分钟数
        long millis = duration.toMillis();//相差毫秒数
        long nanos = duration.toNanos();//相差的纳秒数
        System.out.println(now);
        System.out.println(end);
        long daysDiff = ChronoUnit.DAYS.between(now, end);
        System.out.println("两个时间之间的天数差为：" + daysDiff);
        System.out.println("发送短信耗时【 "+days+"天："+hours+" 小时："+minutes+" 分钟："+millis+" 毫秒："+nanos+" 纳秒】");
    }
}
