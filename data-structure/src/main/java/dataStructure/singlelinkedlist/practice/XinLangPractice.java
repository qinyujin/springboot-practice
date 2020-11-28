package dataStructure.singlelinkedlist.practice;

import java.util.Stack;

/**
 * @author :覃玉锦
 * @create :2020-11-07 09:43:00
 * 1、求单链表中有效节点的个数
 * 2、查找单链表中的倒数第k个节点 新浪面试题
 * 3、单链表的反转 腾讯面试题
 * 4、从尾到头打印单链表（百度面试题，1、反向遍历 2、stack栈）
 * 5、合并两个有序的单链表，合并之后依然有序
 */
public class XinLangPractice {
    public static void main(String[] args) {
        MyNodeList myNodeList = new MyNodeList();
        MyNode n1 = new MyNode(5, "工藤新一");
        MyNode n2 = new MyNode(10, "毛利兰");
        MyNode n3 = new MyNode(7, "灰原哀");
        MyNode n4 = new MyNode(8, "毛利小五郎");

        MyNodeList myNodeList1 = new MyNodeList();
        MyNode b1 = new MyNode(1, "野比大雄");
        MyNode b2 = new MyNode(9, "卡卡罗特");
        MyNode b3 = new MyNode(3, "天气之子");
        MyNode b4 = new MyNode(4, "虚空掠夺者");

        myNodeList.add(n1);
        myNodeList.add(n3);
        myNodeList.add(n2);
        myNodeList.add(n4);
//        myNodeList.show();

        myNodeList1.add(b1);
        myNodeList1.add(b3);
        myNodeList1.add(b2);
        myNodeList1.add(b4);

        MyNode myNode = concatNodeList(myNodeList.getHead(), myNodeList1.getHead());
        show(myNode);
    }

    public static void show(MyNode head){
        MyNode cur = head.next;
        if(head.next==null){
            System.out.println("链表为空！");
            return;
        }
        while (cur!=null){
            System.out.println(cur);
            cur=cur.next;
        }
        System.out.println();
}

    public static int getLength(MyNode head){
        MyNode cur = head.next;
        if(cur==null){
            return 0;
        }
        int length = 0;
        while (true){
            length++;
            if(cur.next==null)return length;
            cur=cur.next;
        }
    }

    /**
     * 从尾获取第num个元素     --新浪
     * @param head
     * @param num
     * @return
     */
    public static MyNode getBackNode(MyNode head,int num){
        MyNode cur = head.next;
        if(head==null){
            System.out.println("空链表！");
            return null;
        }
        int length = getLength(head);
        //num越界
        if(length-num<0 || length-num>length-1){
            System.out.printf("您输入的下标不在正确范围内，请输入1 - %d 之间\n",length-1);
            return null;
        }
        for (int i = 0; i < length-num; i++) {
            cur=cur.next;
        }
        return cur;
    }

    /**
     * 采用新链表，把遍历的节点使用头插法插入新节点，最后把原head指向新链表 --腾讯
     * @param head
     */
    public static void reverseLinkedList(MyNode head){
        if(head.next==null || head.next.next==null){
            return;
        }
        MyNode cur = head.next;
        MyNode next =null;
        MyNode reverseList = new MyNode(0,"");
        //遍历所有节点
        while (cur!=null){
            next=cur.next;//保存现在的下一个节点，因为会断链，所以提前保存
            cur.next=reverseList.next;
            reverseList.next=cur;
            cur=next;
        }
        head.next=reverseList.next;
    }

    /**
     * 反向遍历，不改变结构,使用栈的特点实现，遍历出来放入栈即可   --百度
     * @param head
     */
    public static void reverseShow(MyNode head){
        if(head.next==null){
            System.out.println("链表为空！");
            return;
        }
        Stack<MyNode> stack = new Stack<>();
        MyNode cur = head.next;
        while (cur!=null){
            stack.push(cur);
            cur=cur.next;
        }

        while (stack.size()>0){
            System.out.println(stack.pop());
        }
        System.out.println();
    }

    /**
     * 两个链表连接后依然有序
     * @param head1
     * @param head2
     * @return
     */
    public static MyNode concatNodeList(MyNode head1,MyNode head2){
        MyNode concatList = new MyNode(0,"");
        MyNode temp = concatList;
        MyNode cur2 = head2.next;
        MyNode cur1 = head1.next;
        MyNode next = null;

        if(head1.next==null && head2.next==null){
            System.out.println("链表都为空");
            return null;
        }
        if(head1.next==null && head2.next!=null){
            return head2;
        }
        if(head2.next==null && head1.next!=null){
            return head1;
        }

        //可以建立一个新的链表，从两个链表中增序取值
        while (cur1!=null && cur2!=null){
            //cur1 num小
            if(Integer.compare(cur1.num, cur2.num)<0){
                next=cur1.next;
                cur1.next = concatList.next;
                temp.next = cur1;
                temp=temp.next;
                cur1=next;
            }
            //cur2 num大
            else {
                next=cur2.next;
                cur2.next = temp.next;
                temp.next = cur2;
                temp=temp.next;
                cur2=next;
            }
        }
        if(cur1==null){
            temp.next = cur2;
        }
        if(cur2==null){
            temp.next = cur1;
        }
        return concatList;
    }
}

class MyNodeList{
    private MyNode head= new MyNode(0, "");

    public MyNode getHead() {
        return head;
    }

    public void add(MyNode myNode){
        MyNode temp = head;
        while (true){
            //如果是最后一位直接添加
            if(temp.next==null){
                temp.next=myNode;
                break;
            }
            if(temp.next.num>myNode.num){
                myNode.next=temp.next;
                temp.next=myNode;
                break;
            }
            temp=temp.next;
        }
    }

    public void delete(int num){
        MyNode temp = head;
        if(temp.next==null){
            System.out.println("链表为空！");
            return;
        }
        while (true){
            if(temp.next==null){
                System.out.println("未找到该记录！");
                break;
            }
            if(temp.next.num==num){
                temp.next=temp.next.next;
                System.out.println("删除成功！");
                break;
            }
            temp=temp.next;
        }
    }

    public void update(MyNode myNode){
        MyNode temp = head.next;
        if(temp==null){
            System.out.println("链表为空！");
            return;
        }
        while (true){
            if(myNode.num==temp.num){
                temp.data=myNode.data;
                System.out.println("更新成功！");
                break;
            }
            if(temp.next==null){
                System.out.println("未找到该记录！");
                break;
            }
            temp=temp.next;
        }
    }

    public void show(){
        MyNode temp = head.next;
        if(temp==null){
            System.out.println("链表为空！");
            return;
        }
        while (true){
            System.out.println(temp);
            if(temp.next==null){
                break;
            }
            temp=temp.next;
        }
        System.out.println();
    }
}

class MyNode{
    public int num;
    public String data;
    public MyNode next;

    public MyNode() {
    }

    public MyNode(int num, String data) {
        this.num = num;
        this.data = data;
    }

    @Override
    public String toString() {
        return "MyNode{" +
                "num=" + num +
                ", data='" + data + '\'' +
                '}';
    }
}