package com.example.podofarm.db;

import com.example.podofarm.vo.UserVO;
import org.apache.ibatis.session.SqlSession;

public class UserDBManger extends DBManager{

    public static int insertUser(UserVO u) {
        int re = -1;
        SqlSession session = sqlSessionFactory.openSession();
        re = session.insert("insertUser", u);
        session.commit();
        session.close();
        return re;

    }
}
