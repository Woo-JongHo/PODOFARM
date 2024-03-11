package com.example.podofarm.repository;

import com.example.podofarm.db.StudyDBManger;
import com.example.podofarm.vo.StudyVO;
import org.springframework.stereotype.Repository;

@Repository
public class StudyRepository {


    public int createStudy(StudyVO s) {

        return StudyDBManger.createStudy(s);
    }

    public int updateStudyLeader(String id) {
        return StudyDBManger.updateStudyLeader(id);
    }

    public String checkStudycode(String id) {
        return StudyDBManger.checkStudyCode(id);
    }

    public int findStudyCode(String s_code) {
        return StudyDBManger.findStudyCode(s_code);
    }
}
