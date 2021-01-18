package create.builder;

/**
 * @author :覃玉锦
 * @create :2021-01-18 14:11:00
 */
public class Test {
    public static void main(String[] args) {
        //简单的通过调用指挥者就可以构建复杂的对象
        Director director = new Director();
        Product build = director.build(new Worker());
        System.out.println(build.toString());
    }
}
