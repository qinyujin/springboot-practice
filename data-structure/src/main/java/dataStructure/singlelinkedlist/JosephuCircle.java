package dataStructure.singlelinkedlist;

/**
 * @author :覃玉锦
 * @create :2020-11-10 14:18:00
 * 约瑟夫环的链表实现
 */
public class JosephuCircle {
    public static void main(String[] args) {
        CircleSingleLinkList circleSingleLinkList = new CircleSingleLinkList();
        circleSingleLinkList.add(11);
        circleSingleLinkList.show();
        circleSingleLinkList.pop(4);
    }
}

class CircleSingleLinkList{
    private Boy first=new Boy(-1);

    public void add(int nums){
        if(nums<1){
            System.out.println("至少两个小孩！");
            return;
        }
        Boy cur = first;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if(i==1){
            first=boy;
            first.setNext(first);
            cur=first;
        }
        else {
            cur.setNext(boy);
            cur=boy;
            cur.setNext(first);
        }
        }
    }

    public void show(){
        if(first.getNo()==-1){
            System.out.println("没有小孩！");
            return;
        }
        Boy cur = first;
        while (true){
            if(cur.getNext()==first){
                System.out.print(cur.getNo()+" ");
                System.out.println();
                return;
            }
            System.out.print(cur.getNo()+" ");
            cur=cur.getNext();
        }
    }

    /**
     * 每次数到n个人出圈
     * @param n
     */
    public void pop(int n){
        Boy cur = first;
        int count = 0;
        if(n<1 || first.getNo()==-1){
            System.out.println("参数有误");
            return;
        }
        while (cur.getNext()!=cur){
            //如果n=1，自己出去
            if(n==1){
                this.show();
                return;
            }
            count++;
            //找到上一个
            if(count==n-1){
                System.out.println(cur.getNext().getNo());
                cur.setNext(cur.getNext().getNext());
                cur=cur.getNext();
                count=0;
                continue;
            }
            cur=cur.getNext();
        }
        System.out.println(cur.getNo());
    }
}

class Boy {

    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}