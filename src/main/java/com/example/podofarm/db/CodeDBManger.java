package com.example.podofarm.db;

import com.example.podofarm.vo.CodeVO;
import com.example.podofarm.vo.StudyVO;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeDBManger extends DBManager{
    public static int syncRepo(String s) {
        int re = -1;
        SqlSession session = sqlSessionFactory.openSession();
        re = session.insert("code.createStudy", s);
        session.commit();
        session.close();
        return re;
    }


    public static HashMap<String, String> getSolvedByDay(String id) {
        Map<String, String> results = new HashMap<>();
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, String>> rows = session.selectList("code.getSolvedByDay", id);
        session.close();
        for (Map<String, String> row : rows) {
            String date = row.get("C_DATE");
            String solved = String.valueOf(row.get("SOLVED"));
            results.put(date, solved);
        }
        return (HashMap<String, String>) results;
    }


}
