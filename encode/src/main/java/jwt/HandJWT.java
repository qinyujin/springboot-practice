package jwt;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Base64;

/**
 * @author :覃玉锦
 * @create :2020-09-11 08:01:00
 *
 * 手写jwt，使用md5单向加密特性实现签名
 */
public class HandJWT {
    private static String salt = "123456";

    public static void main(String[] args) {
        //header
        JSONObject header = new JSONObject();
        header.put("alg", "HS256");
        //payload
        JSONObject payload = new JSONObject();
        payload.put("phone", "13688538320");

        //sign = md5: payload+salt
        String sign = DigestUtils.md5Hex(payload.toJSONString() + salt);

        String jwt = Base64.getEncoder().encodeToString(header.toJSONString().getBytes()) + "."
                + Base64.getEncoder().encodeToString(payload.toJSONString().getBytes()) + "."
                + sign;
        System.out.println(jwt);

    }
}
