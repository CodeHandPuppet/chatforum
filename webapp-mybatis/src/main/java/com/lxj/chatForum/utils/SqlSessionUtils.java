package com.lxj.chatForum.utils;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtils {
    //让其加载一次就行了
    private static SqlSessionFactory sqlSessionFactory=null;
    private static ThreadLocal<SqlSession> local=new ThreadLocal<>();
    static {
        try {
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory=sqlSessionFactoryBuilder.build(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static SqlSession openSession(){
        if(local.get() ==null){
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            local.set(sqlSession);
        }
        return local.get();
    }
//  传递sqlSession进行关闭
    public static void closeSession(SqlSession sqlSession){
        if(sqlSession!=null){
            local.remove();
            sqlSession.close();
        }
    }
}
