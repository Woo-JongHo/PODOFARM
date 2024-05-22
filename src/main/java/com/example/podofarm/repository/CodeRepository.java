package com.example.podofarm.repository;

import com.example.podofarm.db.CodeDBManger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Repository
public class CodeRepository {
    public int syncRepo(String sCode) {

        return CodeDBManger.syncRepo(sCode);
    }

    public ArrayList<Map<String, String>> getSolvedByDayCurrentMonth(String id) {
        return CodeDBManger.getSolvedByDayCurrentMonth(id);
    }

}
