package com.alight.mybatis.session;

public interface SqlSession {

    <T> T selectOne(String statementName);

    <T> T selectOne(String statementName, Object parameter);

    <T> T getMapper(Class<T> mapperClass);
}
