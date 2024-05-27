package com.example.podofarm.repository;


import com.example.podofarm.db.UserDBManger;
import com.example.podofarm.vo.UserVO;
import org.springframework.stereotype.Repository;

@Repository


public class UserRepository {

    public int checkUser(String id){
        System.out.println("가입유무 레퍼지토리 도착");
        return UserDBManger.checkUser(id);
    }

    public int checkStudy(String id) {
        System.out.println("스터디 유무 레퍼지토리 도착");
        return UserDBManger.checkStudy(id);
    }

    public int insertUser(UserVO u){
        return UserDBManger.insertUser(u);
    }

    public String getName(String id){
        return UserDBManger.getName(id);
    }

}
