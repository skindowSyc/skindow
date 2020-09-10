package com.skindow.jexl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @ Author     ：syc.
 * @ Date       ：Created in 14:08 2020/9/10
 * @ Description：
 * @ Modified By：
 * @ Version:
 */
@Data
@Slf4j
public abstract class AbstractPerson {
    /**
     * 工作时间
     */
    public static List<String> WORK_TIME = Arrays.asList("09:00", "18:00");

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Gender gender;

    /**
     * 工作
     */
    public void work(Date date) {
        Date now = new Date();
        if (!Objects.isNull(date)) {
            now = date;
        }
        Date startWorkTime = getWorkTime(WORK_TIME.get(0));
        Date stopWorkTime = getWorkTime(WORK_TIME.get(1));

        if (now.getTime() < startWorkTime.getTime()) {
            log.info("{} 还未开始工作", this.name);
        } else if (now.getTime() <= stopWorkTime.getTime()) {
            working();
        } else {
            stopWork();
        }
    }

    private Date getWorkTime(String startWorkTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startWorkTime.split(":")[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(startWorkTime.split(":")[1]));
        return calendar.getTime();
    }

    /**
     * 停止工作
     */
    public abstract void stopWork();

    /**
     * 正在工作
     */
    public abstract void working();

    public enum Gender {

        /**
         * 男
         */
        BOY("男"),
        /**
         * 女
         */
        GIRL("女");
        private String gender;

        Gender(String str) {
        }
    }
}
