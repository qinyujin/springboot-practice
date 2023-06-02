package com.spring;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2023-04-24 20:06:00
 * 模仿spring的annotationContext
 */
public class MyApplicationContext {

    private Class configClass;

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private Map<String, Object> singletonObjects = new HashMap<>();

    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public MyApplicationContext(Class configClass) {
        this.configClass = configClass;

        //configClass -> scanPath(target directory) -> find all contain @component class and create BeanDefinition(store in map)
        scan();

        //create bean
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            Object bean = createBean(beanName, beanDefinition);
            singletonObjects.put(beanName, bean);
        }

    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getType();

        Object instance = null;
        try {
            //实例化
            instance = clazz.getConstructor().newInstance();

            //属性注入
            for (Field field : clazz.getDeclaredFields()) {
                //如果有自动注入
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    //实际是先按照类型匹配再按照name，这里简单写了
                    field.set(instance, getBean(field.getName()));
                }
            }

            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
            }

            if (instance instanceof InitializingBean) {
                ((InitializingBean) instance).afterPropertiesSet();
            }

//            new MyBeanPostProcessor().postProcessBeforeInitialization(instance, beanName);
            //after，同理before可以写在前面
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                //instance如果有代理则变成代理对象。AOP
                instance = beanPostProcessor.postProcessAfterInitialization(instance, beanName);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return instance;
    }

    private void scan() {
        //componentScan注解找bean
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScanAnno = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            //配置的扫描路径
            String path = componentScanAnno.value();

            //找到target目录下的路径，而不是编译期的相对路径
            path = path.replace(".", "/");
            ClassLoader classLoader = MyApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            File file = new File(resource.getFile());

            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    String absolutePath = f.getAbsolutePath();

                    absolutePath = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"));
                    absolutePath = absolutePath.replace("\\", ".");

                    try {
                        Class<?> clazz = classLoader.loadClass(absolutePath);

                        if (clazz.isAnnotationPresent(Component.class)) {

                            //是否实现了这个接口
                            if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                                BeanPostProcessor beanPostProcessor = (BeanPostProcessor) clazz.getConstructor().newInstance();
                                beanPostProcessorList.add(beanPostProcessor);
                            }

                            Component componentAnno = clazz.getAnnotation(Component.class);
                            String beanName = componentAnno.value();
                            if ("".equals(beanName)) {
                                //bean的默认类名
                                beanName = Introspector.decapitalize(clazz.getSimpleName());
                            }

                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setType(clazz);

                            if (clazz.isAnnotationPresent(Scope.class)) {
                                Scope scopeAnno = clazz.getAnnotation(Scope.class);
                                String value = scopeAnno.value();
                                // 判断scope
                                beanDefinition.setScope(value);
                            } else {
                                //单例
                                beanDefinition.setScope("singleton");
                            }

                            beanDefinitionMap.put(beanName, beanDefinition);

                        }

                    } catch (ClassNotFoundException | NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public Object getBean(String beanName) {
        if (!beanDefinitionMap.containsKey(beanName)) {
            throw new RuntimeException("cant find bean");
        }

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

        if (beanDefinition.getScope().equals("singleton")) {
            Object singletonBean = singletonObjects.get(beanName);
            if (singletonBean == null) {
                singletonBean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, singletonBean);
            }
            return singletonBean;
        } else {
            //原型
            Object prototypeBean = createBean(beanName, beanDefinition);
            return prototypeBean;
        }
    }
}
