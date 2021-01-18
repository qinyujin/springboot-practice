
package runtime;
/**
 * @author :覃玉锦
 * @create :2021-01-13 15:40:00
 */
class Father{

    public Father() {
        System.out.println("father的构造器");
    }

    public static void showStatic(String str) {
        System.out.println("father " + str);
    }

    public final void showFinal() {
        System.out.println("father show final");
    }

    public void showCommon() {
        System.out.println("father 普通方法");
    }
}
public class Son extends Father{


    public Son() {
        super();
    }

    public Son(int age){
        this();
    }

    public static void showStatic(String str){
        System.out.println("son "+str);
    }

    private void showPrivate(String str){
        System.out.println("son private "+str);
    }

    public void show(){
        //invokestatic
        showStatic("你好");
        //invokestatic
        super.showStatic("good");
        //invokespecial
        showPrivate("hello");
        //invokespecial
        super.showCommon();
        //invokevirtual虽然是virtual，但实际上已经确定了，是非虚方法
        showFinal();
        //虚方法：
        //invokevirtual
        showCommon();
        info();

        //invokeinterface
        MethodInterface in = null;
        in.methodA();
    }

    public void info(){
    }

    public static void main(String[] args) {
        Son s = new Son();
        s.show();
    }

}

interface MethodInterface{
    void methodA();
}