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
        return repository.checkStudyCode(id);
    }

    public String getStudyName(String s_code) {
        return repository.getStudyName(s_code);
    }

    public Object getStudyMember(String s_code) {
        return repository.getStudyMember(s_code);
    }

    public int getTotalMember(String s_code) {
        return repository.getTotalMember(s_code);
    }

    public Object getDday(String s_code) {
        return repository.getDday(s_code);
    }

    public String getStudyCode(String id) {
        return repository.getStudyCode(id);
    }

    public Object getStudyMemberID(String s_code) {
        return repository.getStudyMemberID(s_code);
    }
}
