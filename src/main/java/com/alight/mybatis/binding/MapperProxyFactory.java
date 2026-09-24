package com.alight.mybatis.binding;


import java.lang.reflect.Proxy;
import java.util.Map;

public class MapperProxyFactory<T> {
    private Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(Map<String, String> sqlSessionMap) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSessionMap, mapperInterface);
        return (T) Proxy.newProxyInstance(
                mapperInterface.getClassLoader(),
                new Class[]{mapperInterface},
                mapperProxy
        );
    }
}
