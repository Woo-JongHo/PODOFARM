package com.example.podofarm.service;

import com.example.podofarm.repository.UserRepository;
import com.example.podofarm.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;


    public int checkUser(String id){
        return repository.checkUser(id);
    }

    public int checkStudy(String id) {
        return repository.checkStudy(id);
    }

    public int insertUser(UserVO u){
        System.out.println(u + "VO값 어떻게 넘어오나 확인");
        return repository.insertUser(u);
    }

    public String getName(String id){
        return repository.getName(id);
    }
}
