package com.example.podofarm.controller;

import com.example.podofarm.service.StudyService;
import com.example.podofarm.service.UserService;
import com.example.podofarm.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("main")
    public String main(){

        return "home/home";
    }

    @PostMapping("/login")
    public String checkUser(@RequestBody Map<String, String> data, HttpSession session) {


        System.out.println("login Controller 실행");

        UserVO user = new UserVO();

        // DATA의 값을 DB와 비교합니다
        // 회원이 등록되어있는지, 스터디가 있는지에 따라 login 화면을 다르게 합니다.
        System.out.print("data 값 : " + data);

        //세션에 저장할 유저정보
        String id = data.get("id");
        String s_code = studyService.checkStudyCode(id);
        session.setAttribute("id", id);
        session.setAttribute("s_code", s_code);

        System.out.println("s_code 의 값은? : " + s_code);

        int checkUser = userService.checkUser(id);
        int checkStudy = userService.checkStudy(id);


        if(checkUser == 1){
            //DB를 생성할 필요가 없으니 스터디 유무를 확인합니다.
            System.out.println(checkStudy + "스터디의 값은 다음과 같습니다");
            if(checkStudy ==1){
                System.out.println("스터디가 존재하므로 다음 스터디 값으로 이동합니다");
                session.setAttribute("s_code",s_code);
                System.out.println("redirect:/" + s_code + "/study-mainpage");
                return "redirect:/" + s_code ;
            }else{
                System.out.println("스터디가 존재하지 않으므로 스터디 구하는 mainpage로 넘어갑니다");
                return "/nostudy/nostudy-mainpage";
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
            return "redirect:/nostudy/nostudy-mainpage";
        }

    }




    //회원 등록

}
