package encode;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * @author :覃玉锦
 * @create :2020-09-03 19:47:00
 */
@SpringBootTest
class EncryptPracticeTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void redisTokenEncrypt() throws Exception {
        MyToken myToken = new MyToken(1,2);

        String json = objectMapper.writeValueAsString(myToken);

        System.out.println("token信息："+json);
        Map<Integer, String> keys = RSAUtil.genKeyPair();

        String publicKey = keys.get(0);
        String privateKey = keys.get(1);

        System.out.println("公钥："+publicKey);
        System.out.println("私钥："+privateKey);

        Jedis jedis = new Jedis();
        jedis.auth("123456");

        String authorization = RSAUtil.encrypt(json, publicKey);

        System.out.println("用户认真："+authorization);

        jedis.set("authorization", authorization);
        jedis.set("privateKey", privateKey);
    }

    @Test
    void testAll() throws JSONException {
        String salt = "@jfiiasof.+62z1";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", "ZhangFei");
        jsonObject.put("phone", "13685456812");

        //1599729693678   17.21
        String token = JWT.create()
                .withIssuedAt(new Date(1599730266412L))
                .withExpiresAt(new Date(1599730266412L+(1000*60*5)))
                .withAudience(jsonObject.toString())
                .sign(Algorithm.HMAC256(salt));

        System.out.println("token:"+token);
        System.out.println("当前时间："+new Date().getTime());

        DecodedJWT jwt = JWT.decode(token);
        String audience = jwt.getAudience().get(0);
        System.out.println("audience:"+audience);
        System.out.println("过期时间戳:"+jwt.getExpiresAt());
        System.out.println("签发时间戳:"+jwt.getIssuedAt());

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(salt)).build();

        System.out.println("在有效期之内可以通过，校验是否通过:"+verifier.verify(token));

    }

    @Test
    void test3(){
        String str = "135.asfiasof.4f8q9f48qwf";
        String[] split = str.split("\\.");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
    }

    @Test
    void test4(){
        Optional<String> optional = Optional.ofNullable(new String());
    }
}