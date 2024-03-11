package com.example.podofarm.service;


import com.example.podofarm.repository.StudyRepository;
import com.example.podofarm.vo.StudyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyService {

    @Autowired
    private StudyRepository repository;

    public int findStudyCode(String s_code) {
        return repository.findStudyCode(s_code);
    }


    public int creteStudy(StudyVO s) {
        return repository.createStudy(s);
    }

    public int updateStudyLeader(String id) {
        return repository.updateStudyLeader(id);
    }

    public String checkStudyCode(String id) {
        return repository.checkStudycode(id);
    }
}
