package com.example.podofarm.service;


import com.example.podofarm.repository.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class CodeService {

    @Autowired
    private CodeRepository repository;

    public int syncRepo(String s_code) {
        return repository.syncRepo(s_code);
    }


    public ArrayList<Map<String, String>> getSolvedByDayCurrentMonth(String id) {
        return repository.getSolvedByDayCurrentMonth(id);
    }
}
