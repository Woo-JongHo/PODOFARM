package com.example.podofarm.controller;

import com.example.podofarm.service.StudyService;
import com.example.podofarm.service.UserService;
import com.example.podofarm.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;


@Controller
public class HomeController {



    @Autowired
    private UserService userService;

    @Autowired
    private StudyService studyService;

    //01 로그인시 DB와 매치해서 값이 있나 확인
    //02 없으면 DB 생성 후 nostudy로 넘어가기
    //03 DB가 있으면 study 유무에 따라 study nostudy로 넘기기

    
    //podofarm 으로 도메인 변경할 것
    @GetMapping("pf")
    public String main(){
         
        return "ver4/home";
    }

    @PostMapping("/login")
    public String checkUser(@RequestBody Map<String, String> data, HttpSession session) {

        UserVO user = new UserVO();

        // DATA의 값을 DB와 비교합니다
        // 회원이 등록되어있는지, 스터디가 있는지에 따라 login 화면을 다르게 합니다.

        //세션에 저장할 유저정보
        String id = data.get("id");
        String s_code = studyService.checkStudyCode(id);
        session.setAttribute("id", id);
        session.setAttribute("s_code", s_code);


        int checkUser = userService.checkUser(id);
        int checkStudy = userService.checkStudy(id);


        if(checkUser == 1){
            //DB를 생성할 필요가 없으니 스터디 유무를 확인합니다.
            System.out.println(checkStudy + "스터디의 값은 다음과 같습니다");
            if(checkStudy == 1){
                session.setAttribute("s_code",s_code);
                return "ver4/main.html";
            }else{
                return "ver4/main.html";
            }
        }else{
            user.setId(data.get("id"));
            user.setName(data.get("name"));
            user.setGender("N");
            user.setPhone("N");
            user.setStudy("0");
            user.setSolved(0);
            user.setLeader(0);
            user.setEmail(data.get("email"));
            int insertUser = userService.insertUser(user);
            System.out.println("회원등록완료");
            return "";
        }

    }
    //회원 등록

}
