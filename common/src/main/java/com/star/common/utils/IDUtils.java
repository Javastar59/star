package com.star.common.utils;

import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.Objects;
import java.util.UUID;

/**
 * id生成工具：
 * uuid、8位uuid（重复率千万分之一）、去重复毫秒id、
 * 雪花算法id（workerId 工作ID 0，datacenterId 数据中心ID 0）、
 * 叶子id（重复率百万分之一）
 */
public class IDUtils {

    private static SnowflakeIdWorker snowflakeIdWorker;

    private static long lastTimestamp = -1L;

    private static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    /**
     * 8位短码uuid
     * 62个可打印字符，通过随机生成32位UUID，
     * 由于UUID都为十六进制，所以将UUID分成8组，
     * 每4个为一组，然后通过模62操作，结果作为索引取出字符
     * 重复率为：千万分之一
     *
     * @return
     */
    public static String shotuuid() {
        String uuid = uuid();
        StringBuffer shortBuffer = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }


    /**
     * 8次uuid解析加随机数，重复几率为百万分之一
     *
     * @return
     */
    public static Long leaf() {
        long result = 0;
        String uuid = uuid();
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            result += x * Math.pow(10, i);
        }
        result += (Math.random() * 10) * Math.pow(10, 9);
        return result;
    }

    /**
     * 常见的方式。可以利用数据库也可以利用程序生成，一般来说全球唯一
     * 优点：本地生成，无中心，无性能瓶颈
     * 缺点：无序，过长
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 雪花算法:twitter生成全局ID生成器的算法策略。
     * 优点：有序，Long型
     * 缺点：中心生成，独立部署ID生成器
     *
     * @return
     */
    public static String snowflake() {
        return String.valueOf(getSnowflakeIdWorker().nextId());
    }

    /**
     * 懒汉模式获取
     *
     * @return
     */
    private static SnowflakeIdWorker getSnowflakeIdWorker() {
        if (Objects.isNull(snowflakeIdWorker)) {
            synchronized (IDUtils.class) {
                if (Objects.isNull(snowflakeIdWorker)) {
                    snowflakeIdWorker = new SnowflakeIdWorker(0, 0);
                }
            }
        }
        return snowflakeIdWorker;
    }


    private static long nowNano() {
        return LocalTime.now().getLong(ChronoField.NANO_OF_DAY);
    }

    /**
     * 雪花算法实现内部类
     */
    static class SnowflakeIdWorker {

        /**
         * 开始时间截 (2016-01-01)
         */
        private final long twepoch = 1454315864414L;
        /**
         * 机器id所占的位数
         */
        private final long workerIdBits = 5L;
        /**
         * 数据标识id所占的位数
         */
        private final long datacenterIdBits = 5L;
        /**
         * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
         */
        private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
        /**
         * 支持的最大数据标识id，结果是31
         */
        private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
        /**
         * 序列在id中占的位数
         */
        private final long sequenceBits = 12L;
        /**
         * 机器ID向左移12位
         */
        private final long workerIdShift = sequenceBits;
        /**
         * 数据标识id向左移17位(12+5)
         */
        private final long datacenterIdShift = sequenceBits + workerIdBits;
        /**
         * 时间截向左移22位(5+5+12)
         */
        private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
        /**
         * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
         */
        private final long sequenceMask = -1L ^ (-1L << sequenceBits);
        /**
         * 工作机器ID(0~31)
         */
        private long workerId;
        /**
         * 数据中心ID(0~31)
         */
        private long datacenterId;
        /**
         * 毫秒内序列(0~4095)
         */
        private long sequence = 0L;
        /**
         * 上次生成ID的时间截
         */
        private long lastTimestamp = -1L;

        /**
         * 构造函数
         *
         * @param workerId     工作ID (0~31)
         * @param datacenterId 数据中心ID (0~31)
         */
        public SnowflakeIdWorker(long workerId, long datacenterId) {
            if (workerId > maxWorkerId || workerId < 0) {
                throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
            }
            if (datacenterId > maxDatacenterId || datacenterId < 0) {
                throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
            }
            this.workerId = workerId;
            this.datacenterId = datacenterId;
        }

        /**
         * 获得下一个ID (该方法是线程安全的)
         *
         * @return SnowflakeId
         */
        public synchronized long nextId() {
            long timestamp = timeGen();
            //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
            if (timestamp < lastTimestamp) {
                throw new RuntimeException(
                        String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
            }
            //如果是同一时间生成的，则进行毫秒内序列
            if (lastTimestamp == timestamp) {
                sequence = (sequence + 1) & sequenceMask;
                //毫秒内序列溢出
                if (sequence == 0) {
                    //阻塞到下一个毫秒,获得新的时间戳
                    timestamp = tilNextMillis(lastTimestamp);
                }
            } else {
                //时间戳改变，毫秒内序列重置
                sequence = 0L;
            }
            //上次生成ID的时间截
            lastTimestamp = timestamp;
            //移位并通过或运算拼到一起组成64位的ID
            return ((timestamp - twepoch) << timestampLeftShift)
                    | (datacenterId << datacenterIdShift)
                    | (workerId << workerIdShift)
                    | sequence;
        }

        /**
         * 阻塞到下一个毫秒，直到获得新的时间戳
         *
         * @param lastTimestamp 上次生成ID的时间截
         * @return 当前时间戳
         */
        private long tilNextMillis(long lastTimestamp) {
            long timestamp = timeGen();
            while (timestamp <= lastTimestamp) {
                timestamp = timeGen();
            }
            return timestamp;
        }

        private long timeGen() {
            return LocalTime.now().getLong(ChronoField.NANO_OF_DAY);
        }

    }


}
