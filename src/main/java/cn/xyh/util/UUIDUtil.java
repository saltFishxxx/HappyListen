package cn.xyh.util;

import java.util.UUID;

/**
 * 产生随机序列
 */
public class UUIDUtil {
    private UUIDUtil() {}

    public static String getUUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
