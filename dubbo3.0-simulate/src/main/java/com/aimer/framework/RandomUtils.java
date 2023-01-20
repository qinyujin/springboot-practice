package com.aimer.framework;

import java.util.List;
import java.util.Random;

/**
 * @Author:yujinqin
 * @Date:2023/1/20 10:31
 */
public class RandomUtils {
    public static URL getRandomUrl(List<URL> urls) {
        return urls.get(new Random().nextInt(urls.size()));
    }
}
