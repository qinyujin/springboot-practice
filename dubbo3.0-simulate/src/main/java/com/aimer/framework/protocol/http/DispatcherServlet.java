package com.aimer.framework.protocol.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:yujinqin
 * @Date:2023/1/19 17:03
 * <p>
 * 请求分发器
 */
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //processing all req
        new HttpServerHandler().handler(req, resp);
    }
}
