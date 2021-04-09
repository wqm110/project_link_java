package com.ruoyi.project.client;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.IdUtils;

import java.sql.Date;

public class Test {
    public static void main(String[] args) {
//        Date endTime = plClientServiceHis.getEndTime();
//        Date startTime = plClientServiceHis.getStartTime();
//        String date = DateUtils.getDate();
//        String s = DateUtils.datePath();
//        Date date1 = Date.valueOf("2020-02-21");
//        System.out.println("s = " + s);
//        System.out.println("date1 = " + date1);
        Long s = IdUtils.snowLId();
        System.out.println("s = " + s);
    }
}
