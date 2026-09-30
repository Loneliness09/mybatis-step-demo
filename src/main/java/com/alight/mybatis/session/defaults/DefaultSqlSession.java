package com.alight.mybatis.session.defaults;

import com.alight.mybatis.binding.MapperRegistry;
import com.alight.mybatis.session.SqlSession;

import java.util.Arrays;

public class DefaultSqlSession implements SqlSession {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return selectOne(statement, null);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("模拟执行 xml 的 SQL 操作: " + statement + " 入参: " + Arrays.toString((Object[]) parameter));
    }

    @Override
    public <T> T getMapper(Class<T> mapperClass) {
        return mapperRegistry.getMapper(mapperClass, this);
    }
}
