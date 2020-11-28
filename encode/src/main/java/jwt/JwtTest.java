package jwt;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

/**
 * @author :覃玉锦
 * @create :2020-09-10 09:11:00
 */
public class JwtTest {

    private static String privateKey = "65SN5bac5pax6IeO4Y6w652J54Ox47W047Cx4LqX45+X5ba/7ISt5ImB7YWf5IuM";

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", 2);
        jsonObject.put("workId", 53778);
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        jsonObject.put("rids",objects);
        System.out.println("传递的json串："+jsonObject.toString());

        String token = JWT.create().withAudience(jsonObject.toString())
                .sign(Algorithm.HMAC256(privateKey));

        System.out.println("获得token："+token);

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(privateKey)).build();
        try {
            verifier.verify(token);
            DecodedJWT jwt = JWT.decode(token);
            System.out.println("token校验成功！");
            System.out.println("用户数据："+jwt.getAudience().get(0));
            User user = objectMapper.readValue(jwt.getAudience().get(0), User.class);
            System.out.println("将json串转换成java类型："+user);
            System.out.println("uid:"+user.getUid());
            System.out.println("workId:"+user.getWorkId());
            System.out.println("rids:"+user.getRids().get(0));
        } catch (JWTVerificationException | JsonProcessingException e) {
            System.out.println("token无效！");
        }
    }
}
