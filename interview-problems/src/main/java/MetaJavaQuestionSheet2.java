// 本试卷唯一 ID: 78E180B61B36FC8AC4E5B1716FCE2C32, 请勿修改本行内容
/* 注意, 这里不要写包名. 留空 */
/* 这里如果用到其他的类, 记得要引用, 否则编译不过 */

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 回答方式: 直接保存或者复制本java文件, 然后在原处作答. 建议重命名成 meta-java-<姓名>.java
 * 选择题改变量赋值的字符串
 * 实现题在原本的函数体里返回正确答案, 注意不要改动函数结构. 用这一个Java文件完成
 * 本卷直接用代码判卷, 没有人工干预. 格式错误, 语法错误, 格式改动会导致试卷无效
 *
 * 注: 一个java文件可以有多个类, 但只能有一个public类. 所以你的实现中可以建内部类, 辅助类. 可以javac编译确认自己语法无误
 * 以下带public static修饰的实现题方法, 不要改变这个签名
 *
 *
 * 这里不要更改默认的类名, 也不要更改修饰符不要加public. 类名保持为 MetaJavaQuestionSheet
 * 可以改文件名, 建议改为 meta-java-<姓名>.java
 */
class MetaJavaQuestionSheet2 {

    /* qn0:
        只是样例! 不用改动

        A. 也选我, 选我就得分

        B. 别选我

        C. 选我, 选我就得分

        D. 别选我
    */
    public static String qn0 = "AC";



    /* qn1:
        下面代码会输出怎样的结果:
        public class A {

            class Inner {
                public String  v1 = "Fake News";
                public String v2 = "Go ahead";
            }

            private static String GetVal() {
                try {
                    return Inner.class.newInstance().v1;
                } catch (Exception e) {
                    try {
                        return Inner.class.getDeclaredConstructor(A.class).newInstance((A)null).v2;
                    } catch (Exception ee) {
                        ee.printStackTrace();
                        return "Fake News, Go ahead";
                    }
                }
            }
            public static void main(String[] args) {

                System.out.println(GetVal());
            }
        }

        A. Fake News, Go ahead

        B. Fake News

        C. Go ahead

        D. 以上都不对
    */
    public static String qn1 = "C";



    /* qn2:
        以下代码编译时哪一行会出错？

        1 package com.metaapp.solution;
        2 public class Solution {
        3     int p1 = 0;
        4     int p2 = 0;
        5     public Solution(int arg){
        6         p1 = arg;
        7     }
        8     public static void main(String args[]){
        9         Solution s1,s2;
       10         int m,n;
       11         m=1;n=2;
       12         s1 = new Solution();
       13         s2 = new Solution(n);
       14     }
       15 }

        A. Line 12

        B. Line 9

        C. Line 6

        D. Line 2
    */
    public static String qn2 = "A";



    /* qn3:
        main()方法如下，try中可以捕获两种类型的异常，如果在该方法运行中产生了一个IOException，将会输出怎样的结果:
          public static void main(String[] args) {
              System.out.print(method(0));
          }
          private static Integer method(Integer i){
              try{
                  if(i++ > 0)
                      throw new IOException();
                  return i++;
              }catch (IOException e){
                  i++;
                  return i++;
              }catch (Exception e){
                  i++;
                  return i++;
              }finally {
                  return i++;
              }
          }

        A. 2

        B. 4

        C. 3

        D. 会抛错 没有输出
    */
    public static String qn3 = "B";



    /* qn4:
        下面代码会分别输出怎样的结果:
        String s = new String(new char[] {'没','人','比','我','更','懂','j','a','v','a'});
        String si = "没人比我更懂java";
        System.out.println(s == si);
        System.out.println(s.intern() == "没人比我更懂java");
        System.out.println(s == si.intern());

        A. true false true

        B. false true false

        C. true true true

        D. false true true
    */
    public static String qn4 = "B";



    /* qn5:
        下面这段程序当n=10的输出是()
      1 public int calc(int n) {
      2     try {
      3         n+=1;
      4         if(n/0 > 0) {
      5             n+=1;
      6         } else {
      7             n-=10;
      8         }
      9         return n;
      10     } catch(Exception e) {
      11         n++;
      12     }
      13     n++;
      14     return n++;

        A. 抛出异常

        B. 12

        C. 14

        D. 0

        E. 13
    */
    public static String qn5 = "E";



    /* qn6:
        public class Queue {
          private int size;
          private int[] data;
          private int front, rear;

          public Queue(int size) {
              this.size = size;
              data = new int[size];
              front = 0; rear = 0;
          }
      }
      int MaxSize=10;
      Quene q = new Queue(MaxSize);

      对于q来说，以下能判断队列满的条件是()

        A. q.front == q.rear;

        B. q.front + q.rear == MaxSize;

        C. q.front - q.rear == MaxSize;

        D. q.front == (q.rear+1) % MaxSize;
    */
    public static String qn6 = "D";



    /* qn7:
        下面的代码在java(jdk8)最终会产生几个String对象:
        String a = "没人";
        String b = "比我";
        String c = "更懂";
        String d = "java";
        String s = a + b + c + d;

        A. 8

        B. 7

        C. 6

        D. 5
    */
    public static String qn7 = "C";



    /* qn8:
        method()方法如下，method()如果调用下面5个不同的update()方法，哪些SQL会被回滚 (多选):
        @Service
        public class TestService {
            @Resource
          TestService testService;

          public void method() {
            1. update1();
            ======================
            2. testService.update2();
            ======================
            3. testService.update3();
            ======================
            4. testService.update4();
            ======================
            5. testService.update5();
          }
          @Transactional
          public void update1() {
            //SQL_1
            throw new Exception();
          }
          @Transactional
          public void update2() {
            //SQL_2
            throw new Exception();
          }
          @Transactional
          private void update3() {
            //SQL_3
            throw new Exception();
          }
          @Transactional
          public void update4() {
            //SQL_4
            throw new Error();
          }
          @Transactional
          public void update5() {
            //SQL_5
            throw new IOException();
          }
        }

        A. SQL_4

        B. SQL_5

        C. SQL_3

        D. SQL_2

        E. SQL_1
    */
    public static String qn8 = "AE";



    /* qn9:
        若进栈序列为a，b，c，d，e，f，进栈和出栈可以穿插进行，则不可能出现的出栈序列是()

        A. b，d，c，a，e，f

        B. b，c，e，a，f，d

        C. c，b，d，a，f，e

        D. d，c，b，a，e，f
    */
    public static String qn9 = "D";



    /* qn10:
        一棵二叉树后序遍历的节点顺序是: 6 4 5 2 7 3 1 ，中序遍历是: 6 4 2 5 1 3 7 ，则前序遍历结果为:

        A. 1 2 4 6 5 7 3

        B. 1 2 3 4 5 6 7

        C. 1 2 4 5 6 3 7

        D. 1 2 4 6 5 3 7
    */
    public static String qn10 = "B";



    /* qn11:
        下面代码会分别输出怎样的结果:
          public static void main(String[] args) {
              Thread t = new Thread() {
                  public void run() {
                      cnn();
                  }
              };
              t.run();
              System.out.print("FakeNews ");
          System.out.print("; ");
              t.start();
              System.out.print("FakeNews ");
          }
          static void cnn() {
              System.out.print("CNN ");
          }

        A. CNN FakeNews ; CNN FakeNews 和FakeNews CNN 都有可能

        B. CNN FakeNews 和FakeNews CNN 都有可能 ; CNN FakeNews

        C. CNN FakeNews ; CNN FakeNews

        D. CNN FakeNews 和FakeNews CNN 都有可能 ; CNN FakeNews 和FakeNews CNN 都有可能
    */
    public static String qn11 = "A";



    /* qn12:
        下列代码的输出结果不可能是:
        private static volatile int s = 0;
        private static final ThreadPoolExecutor async = new ThreadPoolExecutor(
                0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS, new SynchronousQueue<>());
        public static void main(String[] args) throws InterruptedException {
            for (int i = 0; i < 10000; i++) {
                async.execute(()-> s++);
            }
            Thread.sleep(5000L);
            System.out.println(s);
        }

        A. 9998

        B. 9999

        C. 10000

        D. 10001
    */
    public static String qn12 = "D";



    /* qn13:
        "没人比我更懂java".toCharArray(), 在java(jdk8)中关于这个字符数组char[], 以下说法正确的是:

        A. 这个字符串在内存中总共占用 10 byte

        B. 以上说法都不对

        C. 这个字符串在内存中总共占用 16 byte

        D. 这个字符串在内存中总共占用 20 byte
    */
    public static String qn13 = "A";



    /* qn14:
        下面代码会分别输出怎样的结果:
          public static void main(String[] args) {
              Map<String, Object> map = new HashMap<>();
              String str = "没人比我更懂java";
              StrObject obj = new StrObject("没人比我更懂java");
              map.put("str", str);
              map.put("obj", obj);

              str = "真的没人比我更懂java";
              System.out.printf(map.get("str").toString()+"; ");

              StrObject new_obj = (StrObject) map.get("obj");
              new_obj.setStr("真的没人比我更懂java");
              System.out.printf(map.get("obj").toString()+"; ");
          }
          static class StrObject{
              String str;
              public StrObject(String str){
                  this.str = str;
              }
              public void setStr(String str){
                  this.str = str;
              }
              @Override
              public String toString() {
                  return str;
              }
          }

        A. 真的没人比我更懂java; 真的没人比我更懂java;

        B. 没人比我更懂java; 没人比我更懂java;

        C. 真的没人比我更懂java; 没人比我更懂java;

        D. 没人比我更懂java; 真的没人比我更懂java;
    */
    public static String qn14 = "A";



    /* qn15:
        已知一个完全二叉树的第6层有3个叶子结点，则整个二叉树的结点数最多有

        A. 122

        B. 121

        C. 34

        D. 45
    */
    public static String qn15 = "C";




    /*
     * 以下是实现题
     */
    /**
     * <b>注意! 本题不要遍历二维数组. 要求时间复杂度严格低于n^2, 否则视为不得分 </b>
     *
     * 现有一个n*n的二维正整数数组nums，每行元素保证递增，每列元素保证递增，求某正整数x是否存在于该二维数组中，需要尽量优化时间和空间复杂度；
     * @param int[][] nums
     * @param int x 目标数
     * @return boolean
     */
    public static boolean searchMatrix(int[][] nums, int x) {
        // Todo your code goes here...
        //首先找到最右上角的元素，然后小于往左，大于往右来二分
        int row = 0,col = nums[0].length-1;
        while (row <=nums.length-1 && col >=0){
            if(nums[row][col] == x){
                return true;
            }
            else if(nums[row][col] > x){
                col--;
            }
            else {
                row++;
            }
        }
        return false;
    }

    public static Set<String> showMap(Map<String, Object> map) {
        //TODO your code goes here...
        int a=0;
        HashSet<String> wangxuan = new HashSet<>();
        for (String s : map.keySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s);
            Object obj = map.get(s);
            if (obj instanceof String || obj instanceof Number){
                stringBuilder.append("=").append(obj);
                wangxuan.add(stringBuilder.toString());
            }else if(obj instanceof Object[]){
                showArray(wangxuan, stringBuilder, (Object[]) obj);
            }else {
                showMap(wangxuan,stringBuilder,(Map<String, Object>) obj);
            }

            a++;
        }
        return wangxuan;
    }

    private static void showArray(HashSet<String> strings, StringBuilder Jianzao, Object[] obj) {
        Object[] objects= obj;
        String str="";
        for (int i = 0; i < objects.length; i++) {
            str=Jianzao.toString();
            Jianzao.append("[").append(i).append("]");
            Object object = objects[i];
            if (object instanceof String || object instanceof Number || object==null){
                Jianzao.append("=").append(object);
                strings.add(Jianzao.toString());
            }else if (object instanceof Map){
                showMap(strings, Jianzao, (Map<String, Object>) object);
            }else {
                showArray(strings,Jianzao,(Object[]) object);
            }
            Jianzao=new StringBuilder(str);
        }
    }

    private static void showMap(HashSet<String> strings, StringBuilder stringBuilder, Map<String, Object> hashMap) {
        for (String ss : hashMap.keySet()) {
            stringBuilder.append(".").append(ss);
            Object object = hashMap.get(ss);
            if (object instanceof String || object instanceof Number){
                stringBuilder.append("=").append(object);
                strings.add(stringBuilder.toString());
            }else if (object instanceof Object[]){
                showArray(strings,stringBuilder,(Object[]) object);
            }else{
                showMap(strings,stringBuilder,(Map<String, Object>) object);
            }
        }
    }

    /**
     * 给定一个二叉树, 检查它是否是镜像对称的
     * 例如以下是镜像对称的
     *      1
     *     / \
     *    2   2
     *   / \ / \
     *  3  4 4  3
     *
     * 下面这个则不是镜像对称的
     *      1
     *     / \
     *    2   2
     *     \   \
     *      3   3
     *
     * TreeNode类的定义:
     *
     * @param TreeNode 一颗二叉树
     * @return boolean 是否是对称的
     */

    // 以下给出TreeNode类, 请勿修改
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static boolean isTreeSymmetric(TreeNode root) {
        //TODO your code goes here...
        if(root==null)return true;
        return isTreeSymmetric(root.left,root.right);
    }

    public static boolean isTreeSymmetric(TreeNode left,TreeNode right){
        if(left == null && right==null){
            return true;
        }
        //有一边为空了，就已经不对称了
        if(left == null || right == null){
            return false;
        }
        if(left.val!=right.val){
            return false;
        }
        //左的左子需要等于右的右子
        return isTreeSymmetric(left.left,right.right) && isTreeSymmetric(left.right,right.left);
    }
}