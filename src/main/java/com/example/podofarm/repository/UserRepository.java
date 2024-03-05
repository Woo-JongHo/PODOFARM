package com.example.podofarm.repository;


import com.example.podofarm.db.UserDBManger;
import com.example.podofarm.vo.UserVO;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public int insertUser(UserVO u){
        return UserDBManger.insertUser(u);
    }

}
