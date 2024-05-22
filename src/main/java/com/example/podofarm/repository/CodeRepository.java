package com.example.podofarm.repository;

import com.example.podofarm.db.CodeDBManger;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class CodeRepository {
    public int syncRepo(String sCode) {

        return CodeDBManger.syncRepo(sCode);
    }

    public HashMap<String, String> getSolvedByDay(String id) {
        return CodeDBManger.getSolvedByDay(id);
    }

}
