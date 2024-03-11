package com.example.podofarm.db;

import com.example.podofarm.vo.StudyVO;
import org.apache.ibatis.session.SqlSession;

public class StudyDBManger extends DBManager{

    public static int createStudy(StudyVO s) {
        int re = -1;
        SqlSession session = sqlSessionFactory.openSession();
        re = session.insert("study.createStudy", s);
        session.commit();
        session.close();
        return re;

    }

    public static int updateStudyLeader(String id) {
        int re = -1;
        SqlSession session = sqlSessionFactory.openSession();
        re = session.insert("study.updateStudyLeader", id);
        session.commit();
        session.close();
        return re;
    }

    public static String checkStudyCode(String id) {
        SqlSession session = sqlSessionFactory.openSession();
        String result = session.selectOne("study.checkStudyCode", id);

        System.out.println(result);

        session.close();
        return result;
    }

    public static int findStudyCode(String s_code) {
        int re = -1; // 기본값 설정
        SqlSession session = sqlSessionFactory.openSession();
        String result = session.selectOne("study.findStudyCode", s_code); //
        System.out.println(result);
        if (result != null) { // null 체크
            return 1;
        }
        session.close();
        return re;
    }
}
