package classloader;

import sun.misc.Launcher;

import java.net.URL;

/**
 * @author :覃玉锦
 * @create :2021-01-08 23:28:00
 */
public class ClassLoaderTest1 {
    public static void main(String[] args) {
        System.out.println("******启动类加载器*****");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL.toExternalForm());
        }

        System.out.println("******扩展类加载器*****");
        String extDirs = System.getProperty("java.ext.dirs");
        /*String[] split = extDirs.split(";");
        for (String s : split) {
            System.out.println(s);
        }*/
    }
}
