package com.alight.mybatis.test;

import com.alight.mybatis.binding.MapperRegistry;
import com.alight.mybatis.session.SqlSession;
import com.alight.mybatis.session.SqlSessionFactory;
import com.alight.mybatis.session.defaults.DefaultSqlSessionFactory;
import com.alight.mybatis.test.dao.ISchoolDao;
import com.alight.mybatis.test.dao.IUserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


@Slf4j
public class ApiTest {

    @Test
    public void test_MapperProxyFactory() {
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("com.alight.mybatis.test.dao");

        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        String res = userDao.queryUserName("10001");
        log.info("userDao 测试结果: {}", res);

        ISchoolDao schoolDao = sqlSession.getMapper(ISchoolDao.class);
        log.info("schoolDao 测试结果: {}", schoolDao.querySchoolName("10001"));
    }
}
