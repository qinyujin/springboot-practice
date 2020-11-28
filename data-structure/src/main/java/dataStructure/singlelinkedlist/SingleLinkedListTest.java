package dataStructure.singlelinkedlist;

/**
 * @author :覃玉锦
 * @create :2020-11-06 21:03:00
 */
public class SingleLinkedListTest {
    public static void main(String[] args) {
        HeroNode h1 = new HeroNode(1, "林冲", "豹子头");
        HeroNode h2 = new HeroNode(2, "吴用", "智多星");
        HeroNode h3 = new HeroNode(3, "宋江", "及时雨");
        HeroNode h4 = new HeroNode(4, "卢俊义", "玉麒麟");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add2(h1);
        singleLinkedList.add2(h2);
        singleLinkedList.add2(h3);
        singleLinkedList.add2(h4);

        singleLinkedList.showLinkedList();
        HeroNode heroNode = new HeroNode(4, "送姜", "the shy");
        singleLinkedList.updateNode(heroNode);
        singleLinkedList.showLinkedList();

        singleLinkedList.deleteNode(1);
        singleLinkedList.deleteNode(2);
        singleLinkedList.deleteNode(3);
        singleLinkedList.deleteNode(4);
        singleLinkedList.showLinkedList();
    }
}

class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    //按照插入顺序添加
    public void add(HeroNode heroNode){
        HeroNode temp = head;
        while (true){
            if(temp.next==null){
                temp.next=heroNode;
                break;
            }
            temp= temp.next;
        }
    }

    //按照no顺序添加
    public void add2(HeroNode heroNode){
        HeroNode temp = head;
        //按照no序列进行数据插入1、找到位置 2、到最后一个
        while (true){
            if(temp.next==null){
                temp.next = heroNode;
                break;
            }
            if(temp.no<=heroNode.no && temp.next.no>heroNode.no){
                heroNode.next=temp.next;
                temp.next=heroNode;
                break;
            }
            temp = temp.next;
        }
    }

    public void updateNode(HeroNode heroNode){
        HeroNode temp = head;
        if(temp.next==null){
            System.out.println("链表为空！");
            return;
        }
        while (true){
            if(temp.no==heroNode.no){
                temp.name=heroNode.name;
                temp.nickname=heroNode.nickname;
                System.out.println("修改成功！");
                break;
            }
            if(temp.next==null || heroNode.no==0){
                System.out.println("未找到该条记录！");
                break;
            }
            temp=temp.next;
        }
    }

    public void deleteNode(int no){
        HeroNode temp = head;
        if(head.next==null){
            System.out.println("链表为空！");
            return;
        }
        while (true){
            if(temp.next==null || no==0){
                System.out.println("没有找到该记录！");
                break;
            }
            if(temp.next.no==no){
                temp.next=temp.next.next;
                System.out.println("删除节点成功！");
                break;
            }
            temp= temp.next;
        }
    }

    public void showLinkedList(){
        HeroNode temp = head.next;
        if(temp==null){
            System.out.println("链表为空！");
            return;
        }
        while (true){
            System.out.println(temp);
            if(temp.next==null)break;
            temp=temp.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "heroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}