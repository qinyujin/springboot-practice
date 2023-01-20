package com.aimer.framework.register;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:yujinqin
 * @Date:2023/1/19 17:30
 */
public class LocalRegister {
    private static Map<String, Class> map = new HashMap<>();

    public static void register(String interfaceName, Class implClass) {
        map.put(interfaceName, implClass);
    }

    public static Class get(String interfaceName) {
        return map.get(interfaceName);
    }
}
