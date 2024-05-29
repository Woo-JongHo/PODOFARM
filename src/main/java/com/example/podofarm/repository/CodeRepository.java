package com.example.podofarm.repository;

import com.example.podofarm.db.CodeDBManger;
import com.example.podofarm.vo.CodeVO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CodeRepository {
    public int syncRepo(String sCode) {

        return CodeDBManger.syncRepo(sCode);
    }

    public ArrayList<Map<String, String>> getSolvedByDayCurrentMonth(String id) {
        return CodeDBManger.getSolvedByDayCurrentMonth(id);
    }

    public int getTotalSolvedById(String id) {
        return CodeDBManger.getTotalSolvedById(id);
    }

    public int insertCode(CodeVO code) {
        return CodeDBManger.insertCode(code);
    }

    public List<String> getSolvedTitleDESC(String id) {
        return CodeDBManger.getSolvedTitleDESC(id);
    }
}
