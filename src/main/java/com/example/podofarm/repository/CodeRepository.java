package com.example.podofarm.repository;

import com.example.podofarm.db.CodeDBManger;
import com.example.podofarm.db.StudyDBManger;
import com.example.podofarm.vo.StudyVO;
import org.springframework.stereotype.Repository;

@Repository
public class CodeRepository {
    public int syncRepo(String sCode) {

        return CodeDBManger.syncRepo(sCode);
    }
}
