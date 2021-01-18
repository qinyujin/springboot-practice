package structure.bridge;

/**
 * @author :覃玉锦
 * @create :2021-01-18 18:13:00
 */
public class Client {
    public static void main(String[] args) {
        Computer huipu = new Laptop(new Huipu());
        Computer apple = new Desktop(new Apple());
        huipu.info();
        apple.info();
    }
}
