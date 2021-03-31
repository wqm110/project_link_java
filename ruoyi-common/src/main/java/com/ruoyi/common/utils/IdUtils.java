package com.ruoyi.common.utils;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.core.lang.UUID;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * ID生成器工具类
 *
 * @author ruoyi
 */
public class IdUtils {
    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID() {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 随机UUID
     */
    public static String fastUUID() {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String fastSimpleUUID() {
        return UUID.fastUUID().toString(true);
    }

    /**
     * 雪花id
     *
     * @return
     */
    public static String snowId() {
        return IdUtil.getSnowflake(0, 0).nextIdStr();
    }
    /**
     * 雪花id
     *
     * @return
     */
    public static Long snowLId() {
        return IdUtil.getSnowflake(0, 0).nextId();
    }
    //通用生成编号方法
    public static String currencyGenerateNumber(String numberType) {
        SimpleDateFormat dataTime = new SimpleDateFormat("yyyyMMddhhmmss");
        String data = dataTime.format(new Date());
        data = numberType + data;
        Random random = new Random();
        int i = 100;
        i += random.nextInt(900);
        return data + i;
    }
}
