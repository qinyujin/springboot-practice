package com.aimer.framework.register;

import com.aimer.framework.URL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:yujinqin
 * @Date:2023/1/20 10:28
 * <p>
 * simulate zookeeper to register and find service
 */
public class RemoteRegister {
    //interfaceName mapping much machines
    public static Map<String, List<URL>> map = new HashMap<>();

    public static void register(String interfaceName, List<URL> urls) {
        map.put(interfaceName, urls);
    }

    public static List<URL> get(String interfaceName) {
        return map.get(interfaceName);
    }
}
