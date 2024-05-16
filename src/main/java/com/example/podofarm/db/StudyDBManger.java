package com.example.podofarm.db;

import com.example.podofarm.vo.StudyVO;
import org.apache.ibatis.session.SqlSession;

public class StudyDBManger extends DBManager{

    /*SQL문 종류
    1. 스터디 생성
    2. 스터디 방장 권한 부여
    3. 유저의 스터디 유무 확인 (접속 시)
    4. 스터디 코드 불러오기
    5. 스터디 명 불러오기
    6. 스터디 총 인원 불러오기
    7. 스터디 남은 일 수 불러오기
    */

    // 추가될 SQL문
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
        String result = (String)session.selectOne("study.findStudyCode", s_code); //
        System.out.println(result);
        if (result != null) { // null 체크
            return 1;
        }
        session.close();
        return re;
    }

    public static String getStudyName(String s_code) {
        String n = "";
        SqlSession session = sqlSessionFactory.openSession();
        n = session.selectOne("study.getStudyName");
        session.close();
        return n;
    }

    public static Object getStudyMember(String s_code) {
        String n = "";
        SqlSession session = sqlSessionFactory.openSession();
        n = session.selectOne("study.getStudyName");
        session.close();
        return n;
    }

    public static int getTotalMember(String s_code) {
        int re = -1; // 기본값 설정
        SqlSession session = sqlSessionFactory.openSession();
        String result = session.selectOne("study.getTotalMember", s_code); //
        System.out.println(result);
        session.close();
        return re;
    }

    public static int getDday(String s_code) {
        int re = -1; // 기본값 설정
        SqlSession session = sqlSessionFactory.openSession();
        String result = session.selectOne("study.getDday", s_code); //
        System.out.println(result);
        session.close();
        return re;
    }

    public static String getStudyCode(String id) {
        System.out.println("getStudyCodeDB" + id);
        String n = "id";
        SqlSession session = sqlSessionFactory.openSession();
        n = session.selectOne("study.getStudyCode", id);
        if (n == null){
            n = "id";
        }
        session.close();
        return n;
    }
}
