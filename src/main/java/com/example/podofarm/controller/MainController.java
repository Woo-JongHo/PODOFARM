package com.example.podofarm.controller;


import com.example.podofarm.service.StudyService;
import com.example.podofarm.service.UserService;
import com.example.podofarm.vo.StudyVO;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Random;


@Controller
@Setter
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudyService studyService;


    @GetMapping("/ns")
    public String main(){

        return "/study/study";
    }

    //01 스터디 광장입니다
    // 스터디가 있다면, 스터디가 있는 Url로넘어가고 없으면 스터디 없음을 메인 광장으로 설정하여 기능을 구현합니다.


    //02 스터디 생성에 관한 코드
    //생성하기를 눌렀을 때 DB에 넣어줄 랜덤 생성코드를 만들어줄 로직을 간단히 구현합니다 6자리

    @PostMapping("/createStudy")
    public String createStudy(@RequestBody Map<String, String> data, Model model, HttpSession session){

        StudyVO study = new StudyVO();


        String id = (String)session.getAttribute("id");

        StudyCode();
        data.get("s_name");
        data.get("s_start");
        data.get("s_end");


        //스터디를 추가하는 SQL문과, 서비스가 필요합니다.
        int createStudy = studyService.creteStudy(study);

        return "";
    }

    public String StudyCode() {
        //01 배열은 0~9 와 알파벳으로 구성되어있고, 랜덤으로 배열을 뽑아 5자리로 구성됩니다
        String[] code= new String[5];
        String[] password = {
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"
        };

        Random random = new Random();
        StringBuilder studycode = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(36);
            code[i] = password[index];
        }

        return studycode.toString();
    }

}
