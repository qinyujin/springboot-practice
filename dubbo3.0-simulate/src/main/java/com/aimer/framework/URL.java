package com.aimer.framework;

/**
 * @Author:yujinqin
 * @Date:2023/1/20 10:28
 * <p>
 * 只是demo这样写，实际上dubbo的url还可以存如服务接口全限定名，接口下的方法名等都会保存在url中，dubbo只需要通过k(ip) v(url list/set)
 */
public class URL {
    private String hostname;
    private Integer port;

    public URL() {
    }

    public URL(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
