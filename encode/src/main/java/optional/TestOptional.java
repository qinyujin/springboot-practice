package optional;

import java.util.Optional;

/**
 * @author :覃玉锦
 * @create :2020-09-04 13:53:00
 */
public class TestOptional {
    public static void main(String[] args) {
        Optional.ofNullable(null).ifPresentOrElse(a -> System.out.println("a exists:"+a), () -> {
            throw new RuntimeException("空指针异常!");
        });
    }
}
