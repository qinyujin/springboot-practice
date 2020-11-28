package encode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :覃玉锦
 * @create :2020-09-03 09:45:00
 */
@SpringBootApplication
@RestController
public class EncodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(EncodeApplication.class, args);
    }

    @PostMapping("/test")
    public String test(@RequestParam(value = "uid") int uid,@RequestParam(value = "rid") int rid){
        return "uid:"+uid+" rid:"+rid;
    }

    /**
     * 非关键api接口采用对称加密，在请求中加入签名参数，使用公钥对签名进行解密校验，如果校验通过则可以访问
     * @return
     */
    @PostMapping("/api/test")
    public String secretApiTest(@RequestParam("sign") String sign, @RequestBody MyToken myToken){
        System.out.println("签名："+sign);
        if(sign.equals("QinYuJin")){
            return "签名正确，成功访问，获取数据："+myToken.toString();
        }
        else {
            return "签名错误，拒绝访问";
        }
    }
}
