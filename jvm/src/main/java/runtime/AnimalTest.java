package runtime;

/**
 * @author :覃玉锦
 * @create :2021-01-13 15:10:00
 * 早期绑定与晚期绑定：早期绑定是编译期就能够确定引用的，晚期就是运行时才能确定的（例如一个接口有多个实现类，具体哪个
 * 编译期不确定，运行时才确定）
 */
public class AnimalTest {
    public void animal(Animal animal){
        //这里可以体现出晚期绑定，因为编译时不确定
        animal.eat();
    }

    public void hunter(Hunter hunter){
        //这里可以体现出晚期绑定，因为编译时不确定
        hunter.hunt();
    }
}

class Animal{
    public void eat(){
        System.out.println("动物吃东西");
    }
}

interface Hunter{
    void hunt();
}

class Dog extends Animal implements Hunter{
    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }

    @Override
    public void hunt() {
        System.out.println("狗拿耗子多管闲事");
    }
}

class Cat extends Animal implements Hunter{
    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }

    @Override
    public void hunt() {
        System.out.println("猫抓耗子，天经地义");
    }
}
