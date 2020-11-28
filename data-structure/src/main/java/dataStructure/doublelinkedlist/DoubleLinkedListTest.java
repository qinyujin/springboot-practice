package dataStructure.doublelinkedlist;

/**
 * @author :覃玉锦
 * @create :2020-11-09 15:34:00
 * 双向链表
 */
public class DoubleLinkedListTest {
    public static void main(String[] args) {
        MyNodeList2 myNodeList2 = new MyNodeList2();

        MyNode2 n1 = new MyNode2(1,"张三");
        MyNode2 n2 = new MyNode2(2,"罗翔");
        MyNode2 n3 = new MyNode2(3,"柯南");
        myNodeList2.add(n1);
        myNodeList2.add(n2);
        myNodeList2.add(n3);

        myNodeList2.show();

        myNodeList2.del(3);
        myNodeList2.show();

        MyNode2 node2 = new MyNode2(1, "黑猫警长");
        myNodeList2.update(node2);
        myNodeList2.show();
    }

}

class MyNodeList2{
    private MyNode2 head = new MyNode2(0, "");

    public MyNode2 getHead(){
        return head;
    }

    public int getLength(){
        MyNode2 temp = head.next;
        int count=0;
        if(temp==null){
            return 0;
        }
        while (temp!=null){
            count++;
            temp=temp.next;
        }
        return count;
    }

    /**
     * 添加到双向链表尾
     */
    public void add(MyNode2 myNode2){
        MyNode2 temp = head;
        while (temp.next!=null){
            temp=temp.next;
        }
        temp.next=myNode2;
        myNode2.pre= temp;
    }

    public void del(int no){
        MyNode2 temp = head.next;
        if(head.next==null){
            System.out.println("链表为空！");
            return;
        }

        while (temp!=null){
            if(temp.no==no){
                System.out.println("删除成功！");
                if(temp.next!=null)
                temp.next.pre=temp.pre;
                temp.pre.next=temp.next;
                return;
            }
            temp=temp.next;
        }
        System.out.println("未找到该记录！");
    }

    public void update(MyNode2 myNode2){
        MyNode2 temp = head.next;
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        while (temp!=null){
            if(myNode2.no==temp.no){
                System.out.println("更新成功！");
                temp.data=myNode2.data;
                return;
            }
            temp=temp.next;
        }
        System.out.println("没有找到记录！");
    }

    public void show(){
        MyNode2 temp = head.next;
        while (temp!=null){
            System.out.println(temp);
            temp=temp.next;
        }
        System.out.println();
    }
}

class MyNode2{
    public int no;
    public String data;
    public MyNode2 pre;
    public MyNode2 next;

    public MyNode2() {
    }

    public MyNode2(int no, String data) {
        this.no = no;
        this.data = data;
    }

    @Override
    public String toString() {
        return "MyNode2{" +
                "no=" + no +
                ", data='" + data + '\'' +
                '}';
    }
}