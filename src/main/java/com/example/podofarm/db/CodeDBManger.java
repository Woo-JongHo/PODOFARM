package com.example.podofarm.db;

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

    public static ArrayList<Map<String, String>> getSolvedByDayCurrentMonth(String id) {
        ArrayList<Map<String, String>> results = new ArrayList<>();
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, String>> rows = session.selectList("code.getSolvedByDayCurrentMonth", id);
        session.close();
        for (Map<String, String> row : rows) {
            results.add(row);
        }
        return results;
    }






}
