package structure.component;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-01-19 13:02:00
 * 组合模式，又叫部分-整体模式。粗略的理解就是树的构建。
 */
public class ComponentTest {
    public static void main(String[] args) {
        //CEO
        Employee CEO = new Employee("马云","CEO",1);

        //管理层
        Employee manager1 = new Employee("蔡英文", "Head Manger", 2);
        Employee manager2 = new Employee("孙笑川", "Manger", 3);
        Employee manager3 = new Employee("马宝国", "Manger", 4);


        //执行层
        Employee executor1 = new Employee("打工人一号", "Head Executor", 5);
        Employee executor2 = new Employee("打工人二号", "Executor", 6);
        Employee executor3 = new Employee("打工人三号", "Executor", 7);

        //构建树型关系
        CEO.add(manager1);
        manager1.add(manager2);
        manager1.add(manager3);
        manager2.add(executor1);
        manager3.add(executor1);
        executor1.add(executor2);
        executor1.add(executor3);

        System.out.println(CEO.getName()+":");
        for (Employee employee : CEO.getSub()) {
            System.out.println(employee);
        }

        System.out.println(manager1.getName()+":");
        for (Employee employee : manager1.getSub()) {
            System.out.println(employee);
        }

        System.out.println(executor1.getName()+":");
        for (Employee employee : executor1.getSub()) {
            System.out.println(employee);
        }

    }
}

@Data
class Employee{
    private String name;
    private String title;
    private int no;
    //这里可以提现出树的结构
    private List<Employee> subEmployees;

    public Employee(String name, String title, int no) {
        this.name = name;
        this.title = title;
        this.no = no;
        this.subEmployees = new ArrayList<>(16);
    }

    public void add(Employee e){
        subEmployees.add(e);
    }

    public void remove(Employee e){
        subEmployees.remove(e);
    }

    public List<Employee> getSub(){
        return subEmployees;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", no=" + no +
                '}';
    }
}
