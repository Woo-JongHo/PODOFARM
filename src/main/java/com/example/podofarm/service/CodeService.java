package com.example.podofarm.service;


import com.example.podofarm.repository.CodeRepository;
import com.example.podofarm.vo.CodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public int getTotalSolvedById(String id){
        return repository.getTotalSolvedById(id);
    }

    public int insertCode(CodeVO code) {
        System.out.println(code + "CODEVO 확인");
        return repository.insertCode(code);
    }

    public List<String> getSolvedTitleDESC(String id) {
       return repository.getSolvedTitleDESC(id);
    }
}
