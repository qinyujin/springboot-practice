package com.aimer.component.lambda;

import com.aimer.component.entity.Course;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author :覃玉锦
 * @create :2020-10-12 11:35:00
 */
public class LambdaTest {

    @Test
    public void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("这是飞一样的感觉");
            }
        };
        r1.run();
        System.out.println("****************");

        Runnable r2 = () -> {
            System.out.println("这是沸羊羊的干爹");
        };
        r2.run();
    }

    @Test
    public void test2() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return Integer.compare(o1, o2);
            }
        };
        System.out.println(com.compare(12, 32));
        System.out.println("*****************");

        //lambda表达式的写法
        Comparator<Integer> com1 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return Integer.compare(o1, o2);
        };
        System.out.println(com1.compare(12, 6));
        System.out.println("***********");

        //方法引用
        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(12, 12));
    }

    /**
     * lambda表达式
     * 箭头左边：接口方法的形参
     * 右边：方法体
     * <p>
     * 什么东西可以省略：
     * 1、形参只有一个可以省略括号
     * 2、执行语句只有一句（包括return）可以省略大括号和return
     * <p>
     * <p>
     * 方法引用：
     * 适用范围：抽象方法已有相应方法存在可以实现。
     */
    @Test
    public void test3() {
        Consumer<String> con = str -> System.out.println(str);
        con.accept("北京");

        System.out.println("******************");

        PrintStream out = System.out;
        Consumer<String> con1 = out::println;
        con1.accept("beijing");
    }

    /**
     * 使用对象可以进行方法引用
     */
    @Test
    public void test4() {
        Course c1 = new Course(1, "小明", 3);

        Supplier<String> sup1 = () -> c1.getName();
        System.out.println(sup1.get());

        System.out.println("***************");

        Course c2 = new Course(2, "小王", 4);

        Supplier<String> sup2 = c2::getName;

        System.out.println(sup2.get());
    }

    /**
     * 类的静态方法引用
     */
    @Test
    public void test5() {
        /*Comparator<Integer> com1 = (t1,t2) -> Integer.compare(t1,t2);
        System.out.println(com1.compare(12,31));

        System.out.println("*********************");

        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(12, 6));*/

        Function<Double, Long> fun1 = (d) -> Math.round(d);
        System.out.println(fun1.apply(12.3));

        System.out.println("************************");

        Function<Double, Long> fun2 = Math::round;

        System.out.println(fun2.apply(12.6));
    }

    /**
     * 类的实例方法引用
     */
    @Test
    public void test6() {
        /*Comparator<String> com1 = (t1,t2) -> t1.compareTo(t2);
        System.out.println(com1.compare("abc", "abd"));

        System.out.println("************************");

        Comparator<String> com2 = String :: compareTo;
        System.out.println(com2.compare("abe", "abm"));*/

        Course c1 = new Course(1, "java", 3);

        Function<Course, String> fun1 = c -> c.getName();
        System.out.println(fun1.apply(c1));

        System.out.println("*************************");

        Function<Course, String> fun2 = Course::getName;
        System.out.println(fun2.apply(c1));
    }

    /**
     * 构造器引用->
     * 1、空参
     * 2、带参
     */
    @Test
    public void test7() {
        /*Supplier<Course> sup1 = () -> new Course();
        sup1.get();
        System.out.println("*******************");

        Supplier<Course> sup2 = Course::new;
        sup2.get();*/

        Function<String, Course> fun1 = name -> new Course(name);
        Course java = fun1.apply("Java");
        System.out.println(java);

        System.out.println("***************");

        Function<String, Course> fun2 = Course::new;
        Course web = fun2.apply("Web");
        System.out.println(web);

    }

    /**
     * 数组引用本质上和构造器引用一样,只需要把数组看成一个对象即可
     */
    @Test
    public void test8() {
        Function<Integer, String[]> fun1 = (length) -> new String[length];
        String[] apply = fun1.apply(5);
        System.out.println(Arrays.asList(apply).toString());

        System.out.println("*********************");

        Function<Integer, String[]> fun2 = String[]::new;
        String[] apply1 = fun2.apply(10);
        System.out.println(Arrays.asList(apply1).toString());
    }

    @Test
    public void practice() {
        Long culculate = culculate(500L, l -> l * l);
        System.out.println(culculate);
    }

    public static String toUpper(String str, MyInterface myInterface) {
        return myInterface.getValue(str);
    }

    public static <T, R> R culculate(T l, MyInterface1<T, R> inter) {
        return inter.culculate(l);
    }

    public void happyTime(Double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    public List<String> filterList(List<String> list, Predicate<String> predicate) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                stringArrayList.add(s);
            }
        }
        return stringArrayList;
    }
}
