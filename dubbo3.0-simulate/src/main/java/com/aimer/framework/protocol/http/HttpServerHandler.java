package com.aimer.framework.protocol.http;


import com.aimer.framework.Invocation;
import com.aimer.framework.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author:yujinqin
 * @Date:2023/1/19 17:20
 */
public class HttpServerHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();

            String interfaceName = invocation.getInterfaceName();
            String methodName = invocation.getMethodName();

            //在注册中心发现服务
            Class clazz = LocalRegister.get(interfaceName);
            Method method = clazz.getMethod(methodName, invocation.getParamTypes());
            //调用接口的实例代码
            String result = (String) method.invoke(clazz.newInstance(), invocation.getParams());

            IOUtils.write(result, resp.getOutputStream());

        } catch (IOException | ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}