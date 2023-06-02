package com.spring;

/**
 * @author :覃玉锦
 * @create :2023-04-24 20:26:00
 */
public class BeanDefinition {

    private Class type;
    private String scope;
    //懒加载
    private boolean isLazy;

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isLazy() {
        return isLazy;
    }

    public void setLazy(boolean lazy) {
        isLazy = lazy;
    }
}
