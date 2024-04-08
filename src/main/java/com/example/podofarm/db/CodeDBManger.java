package com.example.podofarm.db;

import com.example.podofarm.vo.CodeVO;
import com.example.podofarm.vo.StudyVO;
import org.apache.ibatis.session.SqlSession;

public class CodeDBManger extends DBManager{
    public static int syncRepo(String s) {
        int re = -1;
        SqlSession session = sqlSessionFactory.openSession();
        re = session.insert("study.createStudy", s);
        session.commit();
        session.close();
        return re;
    }
}
