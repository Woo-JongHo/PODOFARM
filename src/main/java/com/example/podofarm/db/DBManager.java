package com.example.podofarm.db;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBManager {

    public static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String resource = "com/example/podofarm/mapper/sqlMapConfig.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            inputStream.close();
        } catch (Exception e) {
            System.out.println("예외발생 DBManager :"+e.getMessage());
        }
    }

}