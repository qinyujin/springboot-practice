package dataStructure.hashtable;

import lombok.Data;

import java.util.Scanner;

/**
 * @author :覃玉锦
 * @create :2020-12-08 14:09:00
 * 哈希表，java中一般是数组+链表或者数组+二叉树。其中数组的对应下标通过hash计算
 */
public class HashTableDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        EmpHashTable table = new EmpHashTable(8);
        while (flag){
            System.out.println("输入add添加雇员");
            System.out.println("输入list遍历雇员");
            System.out.println("输入exit退出系统");
            String input = scanner.next();
            switch (input) {
                case "add":
                    System.out.println("请输入id");
                    int id = scanner.nextInt();
                    System.out.println("请输入name");
                    String name = scanner.next();
                    table.add(new Emp(id,name));
                    break;
                case "list":
                    table.list();
                    break;
                case "exit":
                    flag = false;
                    break;
                default:
                    System.out.println("输入的指令有错，请重试");
                    break;
            }
        }
    }
}

/**
 * 员工类
 */
@Data
class Emp{
    private int id;
    private String name;
    private Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

/**
 * 链表
 */
@Data
class EmpLinedList{
    private Emp head;

    public void add(Emp emp){
        Emp curEmp = head;
        if(head==null){
            head = emp;
            return;
        }
        while (curEmp.getNext()!=null){
            curEmp = curEmp.getNext();
        }
        curEmp.setNext(emp);
    }

    public void list(int index){
        if(head == null){
            System.out.println("当前"+index+"号链表为空");
            return;
        }
        Emp curEmp = head;
        System.out.print("当前"+index+"号链表：");
        while (curEmp!=null){
            System.out.print(" => "+curEmp);
            curEmp = curEmp.getNext();
        }
        System.out.println();
    }
}

class EmpHashTable{
    private EmpLinedList[] empLinedLists;
    private int size;

    public EmpHashTable(int size) {
        this.size = size;
        empLinedLists = new EmpLinedList[size];
        for (int i = 0; i < size; i++) {
            empLinedLists[i] = new EmpLinedList();
        }
    }

    /**
     * 采用最简单的取模法来计算hash值
     * @param emp
     */
    public void add(Emp emp){
        int empNo = emp.getId() % size;
        empLinedLists[empNo].add(emp);
    }

    /**
     * 遍历
     */
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinedLists[i].list(i);
        }
    }
}