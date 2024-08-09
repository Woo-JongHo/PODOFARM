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
    //podofarm 으로 도메인 변경할 것

    @GetMapping("/pf")
    public String home(HttpSession session){

        String id = (String)session.getAttribute("id");
        //String id = "123456";

        System.out.println("pf 도메인에서 id는 현재 : " + id);

        if (id == null)
            return "ver4/home";
        else{
            String s_code = studyService.getStudyCode(id);

            if (s_code.equals("id")) {
                System.out.println("여기왔는가?");
                return "redirect:/main";
            }
            else {
                System.out.println("s_code redirect");
                return "redirect:/" + s_code;
            }
        }
    }

    @PostMapping("/login")
    public String checkUser(@RequestBody Map<String, String> data, HttpSession session) {

        UserVO user = new UserVO();

        // DATA의 값을 DB와 비교합니다
        // 회원이 등록되어있는지, 스터디가 있는지에 따라 login 화면을 다르게 합니다.
        //세션에 저장할 유저정보
        String id = data.get("id");
        String s_code = studyService.checkStudyCode(id);

        System.out.println(id + " id를 가지고오는가?");
        session.setAttribute("id", id);
        session.setAttribute("s_code", s_code);

        int checkUser = userService.checkUser(id);
        int checkStudy = userService.checkStudy(id);

        if(checkUser == 1){
            //DB를 생성할 필요가 없으니 스터디 유무를 확인합니다.
            if(checkStudy == 1){
                session.setAttribute("s_code",s_code);
                return "redirect:/main";
            }else{
                System.out.println("스터디가없어서 이곳으로 들어오는가");
                return "redirect:ver4/main2";
            }
        }else{
            user.setId(data.get("id"));
            user.setName(data.get("name"));
            user.setGender("N");
            user.setPhone("N");
            user.setStudy("1");
            user.setSolved(0);
            user.setLeader(0);
            user.setEmail(data.get("email"));
            int insertUser = userService.insertUser(user);
            System.out.println("회원등록완료");
            return "ver4/main2";
        }
    }
    //회원 등록

}
