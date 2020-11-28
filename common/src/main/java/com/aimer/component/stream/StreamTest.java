package com.aimer.component.stream;

import com.aimer.component.entity.Course;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author :覃玉锦
 * @create :2020-10-13 16:09:00
 */
public class StreamTest {
    /**
     * 实例化stream的方法，默认创建顺序流，可以创建并行流
     * 方式1、集合
     */
    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        //默认为顺序流
        Stream<Integer> stream = list.stream();
        //并行流,没有固定顺序
        Stream<Integer> parallelStream = list.parallelStream();
    }

    /**
     * 方式2、数组
     */
    @Test
    public void test2() {
        int[] arr = {1, 2, 3};
        IntStream stream = Arrays.stream(arr);
    }

    /**
     * 方式3、使用Stream.of方法
     */
    @Test
    public void test3() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
    }

    /**
     * 方式4、创建无限流（了解）
     */
    @Test
    public void test4() {
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);
    }

    /**
     * 中间操作1,筛选与分割：
     * 1、filter           过滤
     * 2、limit            限制个数
     * 3、skip             跳过
     * 4、distinct         去重
     */
    @Test
    public void test5() {
        List<Course> courses = Course.getCourses();
        Stream<Course> stream = courses.stream();

        //过滤id大等于2的
        stream.filter(c -> c.getId() >= 2).forEach(System.out::println);

        System.out.println();
        //限制，只打印两条数据
        courses.stream().limit(2).forEach(System.out::println);

        System.out.println();
        //跳过，从第3条数据开始打印
        courses.stream().skip(2).forEach(System.out::println);

        System.out.println();
        //去掉重复数据，只留下一条，原理是通过hashcode和equals来判断
        courses.add(new Course(4, "数据结构", 4));
        courses.add(new Course(4, "数据结构", 4));
        courses.add(new Course(4, "数据结构", 4));
        courses.add(new Course(4, "数据结构", 4));
        System.out.println(courses);

        courses.stream().distinct().forEach(System.out::println);
    }

    /**
     * 中间操作2：映射
     * 1、map
     * 2、flatMap
     */
    @Test
    public void test6() {
        List<String> list = Arrays.asList("aa", "bb", "cc");
        list.stream().map(s -> s.toUpperCase()).forEach(System.out::println);
        System.out.println();

        //一般的map，不会识别stream类型，如果映射完之后还是stream类型会直接保留
        list.stream().map(StreamTest::fromStringToStream).forEach(s -> {
            s.forEach(System.out::println);
        });

        System.out.println();
        //flatMap，会把stream类型给拆开，拿到里面的数据
        list.stream().flatMap(StreamTest::fromStringToStream).forEach(System.out::println);
    }

    /**
     * 中间操作3：排序
     * 1、sort()
     * 2、sort(Comparator com)
     */
    @Test
    public void test7() {
        //sort方法会使用自己实现的comparable接口的compare方法,如果没有实现则会报错
        List<Integer> list = Arrays.asList(1, 6, 95, 1, -454, 12, 6);
        list.stream().sorted().forEach(System.out::println);
        System.out.println();

        //由于没有实现comparable接口，会抛出异常
        List<Course> courses = Course.getCourses();
//        courses.stream().sorted().forEach(System.out::println);

        //实现comparator接口方法，可以实现排序.1、按tid排序
        courses.stream().sorted((c1, c2) -> Integer.compare(c1.getTid(), c2.getTid()))
                .forEach(System.out::println);

        System.out.println();
        //2、先按tid，再按name排序
        courses.stream().sorted((c1, c2) -> {
            Integer comValue = Integer.compare(c1.getTid(), c2.getTid());
            if (comValue != 0) return comValue;
            else return c1.getName().compareTo(c2.getName());
        }).forEach(System.out::println);
    }

    /**
     * 终止操作1：匹配、遍历
     * 1、allMatch
     * 2、anyMatch
     * 3、noneMatch
     * 4、findFirst
     * 5、findAny
     * 6、count
     * 7、max
     * 8、min
     * 9、foreach
     */
    @Test
    public void test8() {
        List<Course> list = Course.getCourses();

        //allMatch判断是否所有数据都符合,判断是否所有的tid都大于2
        boolean b = list.stream().allMatch(c -> c.getTid() > 2);
        System.out.println(b);

        //anyMatch判断是否存在数据符合
        boolean b1 = list.stream().anyMatch(c -> c.getTid() > 2);
        System.out.println(b1);

        //noneMatch判断是否不符合
        boolean b2 = list.stream().noneMatch(c -> c.getTid() > 5);
        System.out.println(b2);

        //findFirst查找第一个符合的
        Optional<Course> first = list.stream().findFirst();
        System.out.println(first);

        //findAny随机查找
        Optional<Course> any = list.parallelStream().findAny();
        System.out.println(any);

        //count查找数量
        long count = list.stream().filter(c -> c.getTid() > 2).count();
        System.out.println(count);

        //max，按照自定义comparator来查找
//        Optional<Course> max = list.stream().max((c1, c2) -> Integer.compare(c1.getTid(), c2.getTid()));
        Optional<Integer> max = list.stream().map(c -> c.getTid()).max(Integer::compareTo);
        System.out.println(max);

        //min找最小
        Optional<Course> min = list.stream().min((c1, c2) -> Integer.compare(c1.getTid(), c2.getTid()));
        System.out.println(min);

        //foreach遍历
        list.stream().forEach(System.out::println);
    }

    /**
     * 终止操作2：归约
     * reduce
     */
    @Test
    public void test9() {
        //1、计算1到10的和
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = list.stream().reduce(0, Integer::sum);
        System.out.println(reduce);
        System.out.println("**************");

        //2、计算Course的tid总和
        List<Course> courses = Course.getCourses();
        Optional<Integer> reduce1 = courses.stream().map(c1 -> c1.getTid()).reduce((c1, c2) -> {
            return c1 + c2;
        });
//        Optional<Integer> reduce1 = courses.stream().map(c1 -> c1.getTid()).reduce(Integer::sum);
        System.out.println(reduce1);
    }

    /**
     * 终止操作3：收集
     * collect(collectors.xxx)
     * 把流转换成list、set、collection等
     */
    @Test
    public void test10() {
        List<Course> courses = Course.getCourses();
        List<Course> collect = courses.stream().filter(c -> c.getTid() > 2).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println("***************");
        Set<Course> collect1 = courses.stream().filter(c -> c.getTid() > 2).collect(Collectors.toSet());
        System.out.println(collect1);
    }

    @Test
    public void practice() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        System.out.println("找出 2011 年发生的所有交易， 并按交易额排序（从低到高）");
        transactions.stream().filter(tr -> tr.getYear() == 2011)
                .sorted((tr1, tr2) -> Integer.compare(tr1.getValue(), tr2.getValue()))
                .forEach(System.out::println);

        System.out.println();

        System.out.println("交易员都在哪些不同的城市工作过");
        transactions.stream().map(tr -> tr.getTrader())
                .map(t -> t.getCity())
                .distinct()
                .forEach(System.out::println);

        System.out.println();

        System.out.println("查找所有来自剑桥的交易员，并按姓名排序");
        transactions.stream().map(tr -> tr.getTrader())
                .filter(t -> t.getCity().equals("Cambridge"))
                .sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
                .distinct()
                .forEach(System.out::println);

        System.out.println();

        System.out.println("返回所有交易员的姓名字符串，按字母顺序排序");
        transactions.stream().map(tr -> tr.getTrader())
                .map(t -> t.getName())
                .distinct()
                .sorted()
                .forEach(System.out::println);

        System.out.println();

        System.out.println("有没有交易员是在米兰工作的");
        boolean existMilan = transactions.stream().map(tr -> tr.getTrader())
                .anyMatch(t -> t.getCity().equals("Milan"));
        System.out.println(existMilan);

        System.out.println();

        System.out.println("打印生活在剑桥的交易员的所有交易额");
        Integer integer = transactions.stream().filter(tr -> tr.getTrader().getCity().equals("Cambridge"))
                .map(tr -> tr.getValue())
                .reduce(Integer::sum)
                .get();
        System.out.println(integer);

        System.out.println();

        System.out.println("所有交易中，最高的交易额是多少");
        Integer integer1 = transactions.stream().max((tr1, tr2) -> Integer.compare(tr1.getValue(), tr2.getValue()))
                .map(tr -> tr.getValue())
                .get();
        System.out.println(integer1);

        System.out.println();

        System.out.println("找到交易额最小的交易");
        Transaction transaction = transactions.stream().min((tr1, tr2) -> Integer.compare(tr1.getValue(), tr2.getValue()))
                .get();
        System.out.println(transaction);

    }

    //把字符串转换成流的形式
    public static Stream<Character> fromStringToStream(String str) {
        ArrayList<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }
}
