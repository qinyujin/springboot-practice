/**
 * @author :覃玉锦
 * @create :2020-09-17 10:18:00
 */
public class RegexTest {
    public static void main(String[] args) {
        /*String methodRegex = "(GET)|(POST)|(DELETE)|(PATCH)";
        String GET = "GET";
        String POST = "POST";
        String DELETE = "DELETE";
        String PATCH = "PATCH";

        String url = "/api/user/role/-1";
        String urlRegex = "(\\/api\\/([a-z,\\/,\\d]+))";

        System.out.println(GET.matches(methodRegex));

        System.out.println(url.matches(urlRegex));*/

        String regex = "-*\\d{1,}";
        String path = "/api/user/info/-145646/role/123";
        String s = path.replaceAll(regex, "{id}");
        System.out.println(s);
    }
}
