package com.aimer.gateway.filter;

import com.aimer.component.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2020-08-15 12:21:00
 */
@Component
@Slf4j
public class LoginFilter implements Ordered, GlobalFilter {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("进入globalFilter");

        //权限问题解决办法：下策：（1 地址不带任何数字参数，参数通过?后面传递

        Map<String, String> uriTemplateVariables = ServerWebExchangeUtils.getUriTemplateVariables(exchange);
        System.out.println("获取uri模板变量："+uriTemplateVariables);

        RequestPath p = exchange.getRequest().getPath();
        String path = p.toString();

        //测试可知可以获取header中的值
        HttpHeaders headers = exchange.getRequest().getHeaders();
        HttpHeaders headers1 = exchange.getResponse().getHeaders();
        System.out.println("path:"+path);
        System.out.println("REQUEST HEADERS:"+headers.toString());
        System.out.println("RESPONSE HEADERS:"+headers1.toString());
        List<String> list = headers.get("authorization");
        System.out.println("auth:"+list.get(0));


        /*ServerHttpRequest request = exchange.getRequest().mutate().header("uid", "2").build();
        return chain.filter(exchange.mutate().request(request).build());*/

        ServerHttpRequest request = exchange
                .getRequest()
                .mutate()
                .header("uid","2")
                .header("workId", "53778")
                .header("rids", "[1,2]")
                .build();

        System.out.println("经过globalFilter的header："+request.getHeaders());

        ServerWebExchange exchange1 = exchange.mutate().request(request).build();

        System.out.println(redisUtil.exists("user"));
        //放行登录api
        if (path.equals("/login") || path.equals("/test1")) {
            return chain.filter(exchange1);
        }

        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"测试网关统一异常捕获");
    }

    @Override
    public int getOrder() {
        //值越小执行顺序越靠前
        return 0;
    }
}
