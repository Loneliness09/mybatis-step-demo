package com.alight.mybatis.binding;


import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class MapperProxy<T> implements InvocationHandler, Serializable {

    private Map<String, String> sqlSessionMap;
    private final Class<T> mapperInterface;

    public MapperProxy(Map<String, String> sqlSessionMap, Class<T> mapperInterface) {
        this.sqlSessionMap = sqlSessionMap;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            String key = mapperInterface.getName() + "." + method.getName();
            System.out.println("查找的key = " + key);
            return sqlSessionMap.get(key) + "被代理了.";
        }
    }

}
