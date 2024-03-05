package com.example.podofarm.service;

import com.example.podofarm.repository.UserRepository;
import com.example.podofarm.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public int insertUser(UserVO u){
        return repository.insertUser(u);
    }
}
