package com.example.podofarm.db;

import com.example.podofarm.vo.UserVO;
import org.apache.ibatis.session.SqlSession;

public class UserDBManger extends DBManager{

    /*SQL 목록
    1. 회원가입 유무 확인
    2. 스터디 유무 확인
    3. 회원가입하기
    */

    /*추가 SQL 목록
    *
    *  */

    public static int checkUser(String id){
        System.out.print("디비매니저도착확인");
        int re = -1; // 기본값 설정
        SqlSession session = sqlSessionFactory.openSession();
        String result = session.selectOne("user.checkUser", id); // Integer로 받기
        System.out.println(result);
        if (result != null) { // null 체크
            return 1;
        }
        session.close();
        return re;
    }

    public static int checkStudy(String id) {
        int re = -1; // 기본값 설정
        SqlSession session = sqlSessionFactory.openSession();
        String result = session.selectOne("user.checkStudy", id); // Integer로 받기
        System.out.println(result);
        if (result != null) { // null 체크
            return 1;
        }
        session.close();
        return re;
    }
    public static int insertUser(UserVO u) {
        int re = -1;
        SqlSession session = sqlSessionFactory.openSession();
        re = session.insert("insertUser", u);
        session.commit();
        session.close();
        return re;

    }

}
