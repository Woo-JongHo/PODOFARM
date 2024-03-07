package com.example.podofarm.service;


import com.example.podofarm.repository.StudyRepository;
import com.example.podofarm.vo.StudyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyService {

    @Autowired
    private StudyRepository repository;



    public int creteStudy(StudyVO s) {
        return repository.createStudy(s);
    }
}
