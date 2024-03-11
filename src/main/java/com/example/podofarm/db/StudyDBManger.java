package com.example.podofarm.db;

import com.example.podofarm.vo.StudyVO;
import org.apache.ibatis.session.SqlSession;

public class StudyDBManger extends DBManager{

    public static int createStudy(StudyVO s) {
        int re = -1;
        SqlSession session = sqlSessionFactory.openSession();
        re = session.insert("createStudy", s);
        session.commit();
        session.close();
        return re;

    }

    public static int updateStudyLeader(String id) {
        int re = -1;
        SqlSession session = sqlSessionFactory.openSession();
        re = session.insert("updateStudyLeader", id);
        session.commit();
        session.close();
        return re;
    }
}
