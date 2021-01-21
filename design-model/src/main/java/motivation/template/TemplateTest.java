package motivation.template;

/**
 * @author :覃玉锦
 * @create :2021-01-19 15:39:00
 * 模板方法是事先定义好一些骨架/结构，然后实现类按照大体步骤去实现，
 */
public class TemplateTest {
    public static void main(String[] args) {
        Game basketball = new Basketball();
        Game football = new Football();
        basketball.play();
        football.play();
    }
}

abstract class Game{
    abstract void init();
    abstract void begin();
    abstract void end();

    //模板，使用final修饰可以让具体实现类来按照这个结构来实现
    public final void play(){
        init();
        begin();
        end();
    }
}

//两种游戏
class Basketball extends Game{
    @Override
    void init() {
        System.out.println("篮球场已准备好");
    }

    @Override
    void begin() {
        System.out.println("双方篮球队员开始比赛");
    }

    @Override
    void end() {
        System.out.println("篮球比赛结束");
    }
}

class Football extends Game{
    @Override
    void init() {
        System.out.println("足球场已准备好");
    }

    @Override
    void begin() {
        System.out.println("双方足球队员开始比赛");
    }

    @Override
    void end() {
        System.out.println("足球比赛结束");
    }
}
