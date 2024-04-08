package com.example.podofarm.service;


import com.example.podofarm.repository.CodeRepository;
import com.example.podofarm.repository.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeService {

    @Autowired
    private CodeRepository repository;

    public int syncRepo(String s_code) {
        return repository.syncRepo(s_code);
    }


}
