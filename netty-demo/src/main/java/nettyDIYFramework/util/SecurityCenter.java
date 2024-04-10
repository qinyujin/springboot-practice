package nettyDIYFramework.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author :覃玉锦
 * @create :2024-04-08 19:16:00
 * 模拟缓存和一些安全配置
 */
public class SecurityCenter {
    private static HashMap<String, Boolean> loginUsers = new HashMap<>();

    private static Set<String> whiteList = new HashSet<>();

    static {
        whiteList.add("127.0.0.1");
    }

    public static boolean isDupLog(String remoteAdr) {
        return loginUsers.containsKey(remoteAdr);
    }

    public static boolean isWhiteUser(String ip) {
        return whiteList.contains(ip);
    }

    public static void addLoginUser(String user) {
        loginUsers.put(user, true);
    }

    public static void removeUser(String user) {
        loginUsers.remove(user);
    }
}
