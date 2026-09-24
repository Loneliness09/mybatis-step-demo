package com.alight.mybatis.test;

import com.alight.mybatis.binding.MapperProxyFactory;
import com.alight.mybatis.test.dao.IUserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ApiTest {

    @Test
    public void test_MapperProxyFactory() {
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);

        Map<String, String> sqlSessionMap = new HashMap<>();
        sqlSessionMap.put("com.alight.mybatis.test.dao.IUserDao.queryUserName", "模拟执行:查询姓名");
        sqlSessionMap.put("com.alight.mybatis.test.dao.IUserDao.queryUserAge", "模拟执行:查询年龄");

        IUserDao userDao = factory.newInstance(sqlSessionMap);
        String res = userDao.queryUserName("10001");
        log.info("res={}", res);
    }
}
