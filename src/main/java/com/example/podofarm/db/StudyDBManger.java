package com.example.podofarm.db;

import com.example.podofarm.vo.StudyVO;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String result = session.selectOne("study.findStudyCode", s_code);
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
        n = (String)session.selectOne("study.getStudyName", s_code);
        System.out.println("getStudyName" + n);
        session.close();
        return n;
    }

    public static List<String> getStudyMember(String s_code) {
       List<String> names;
       SqlSession session = sqlSessionFactory.openSession();
       names = session.selectList("study.getStudyMember", s_code);
       System.out.println(names + "스터디원이름");
       return names;
    }
    public static int getTotalMember(String s_code) {
        int re = -1; // 기본값 설정
        SqlSession session = sqlSessionFactory.openSession();
        re = session.selectOne("study.getTotalMember", s_code); //
        session.close();
        return re;
    }

    public static int getDday(String s_code) {
        int re = -1; // 기본값 설정
        SqlSession session = sqlSessionFactory.openSession();
        re = session.selectOne("study.getDday", s_code); //
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

    public static List<String> getStudyMemberIdByMonth(String s_code, String month) {
            List<String> names;
            SqlSession session = sqlSessionFactory.openSession();
        try {
            // 파라미터 맵 생성
            Map<String, Object> params = new HashMap<>();
            params.put("s_code", s_code);
            params.put("s_start", month);

            // 쿼리 실행
            names = session.selectList("study.getStudyMemberIdByMonth", params);
            System.out.println(names + "스터디원아이디");
        } finally {
            session.close();
        }
        return names;
    }

    public static List<Map<String, String>> getRecentActivity(String s_code) {
        List<Map<String, String>> data;
        SqlSession session = sqlSessionFactory.openSession();
        data = session.selectList("study.getRecentActivity", s_code);
        session.close();

        return data;
    }

    public static List<Map<String, String>> getSolvedRank(String s_code) {
        List<Map<String, String>> data;
        SqlSession session = sqlSessionFactory.openSession();
        data = session.selectList("study.getSolvedRank", s_code);
        session.close();

        return data;
    }

    public static String getStartDay(String s_code) {
        String n = "";
        SqlSession session = sqlSessionFactory.openSession();
        n = session.selectOne("study.getStartDay",s_code);
        session.close();
        return n;
    }

    public static Object getStudyMemberByMonth(String s_code, String s_start) {
        List<String> names;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("s_code", s_code);
            params.put("s_start", s_start);

            names = session.selectList("study.getStudyMemberByMonth", params);
        } finally {
            session.close();
        }
        return names;
    }

    public static List<String> getStudyMemberId(String s_code) {
        List<String> names;
        SqlSession session = sqlSessionFactory.openSession();
        names = session.selectList("study.getStudyMemberId", s_code);
        return names;
    }

}
