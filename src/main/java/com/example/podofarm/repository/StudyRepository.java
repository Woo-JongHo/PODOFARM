package com.example.podofarm.repository;

import com.example.podofarm.db.StudyDBManger;
import com.example.podofarm.vo.StudyVO;
import org.springframework.stereotype.Repository;

@Repository
public class StudyRepository {


    public int createStudy(StudyVO s) {

        return StudyDBManger.createStudy(s);
    }
}
