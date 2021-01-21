package structure.facade;

/**
 * @author :覃玉锦
 * @create :2021-01-19 14:14:00
 * 外观模式，提供一个类来给外部访问，隐藏内部实现细节
 */
public class OutviewTest {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();
        shapeMaker.circleShow();
        shapeMaker.rectangleShow();
        shapeMaker.squareShow();
    }
}

interface Shape{
    void info();
}

class Rectangle implements Shape{
    @Override
    public void info() {
        System.out.println("矩形");
    }
}

class Square implements Shape{
    @Override
    public void info() {
        System.out.println("正方形");
    }
}

class Circle implements Shape{
    @Override
    public void info() {
        System.out.println("圆形");
    }
}

//提供一个外观类，供外部来访问。
class ShapeMaker{
    private Shape circle;
    private Shape square;
    private Shape rectangle;

    public ShapeMaker() {
        this.circle = new Circle();
        this.square = new Square();
        this.rectangle = new Rectangle();
    }

    public void circleShow(){
        circle.info();
    }

    public void squareShow(){
        square.info();
    }

    public void rectangleShow(){
        rectangle.info();
    }
}
