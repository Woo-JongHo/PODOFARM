package com.example.podofarm.controller;


import com.example.podofarm.service.CodeService;
import com.example.podofarm.service.StudyService;
import com.example.podofarm.service.UserService;
import com.example.podofarm.vo.StudyVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Map;


@Controller
@Setter
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudyService studyService;

    @Autowired
    private CodeService codeService;

    //스터디가 없을 때 컨트롤러

    @GetMapping("/main")
    public String main(HttpSession session){
        String id = (String) session.getAttribute("id");

        return "ver4/main";
    }

    @PostMapping("/createStudy")
    public String createStudy(@RequestBody Map<String, String> data, Model model, HttpSession session){

        StudyVO study = new StudyVO();

        String id = "123456";
        //  String id = (String)session.getAttribute("id");


        String StudyCode = StudyCode();
        data.get("s_name");
        data.get("s_password");
        data.get("s_start");
        data.get("s_end");

        System.out.println("스터디 코드 생성확인 " +StudyCode);

        study.setId(id);
        study.setS_name(data.get("s_name"));
        study.setS_password(data.get("s_password"));
        study.setS_start(data.get("s_start"));
        study.setS_end(data.get("s_end"));
        study.setS_code(StudyCode);


        System.out.println("스터디 생성 컨트롤러 작동");

        int createStudy = studyService.creteStudy(study);
        int updateStudyLeader = studyService.updateStudyLeader(id);
        return "mainpage-nostudy";
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
            studycode.append(password[index]);
        }

        System.out.println(studycode.toString() + "코드생성값은?");
        return studycode.toString();
    }


    //스터디가 있을 때 컨트롤러 MainPage와 연동
    @GetMapping("/{s_code}")
    public String studyMainPage(Model model, @PathVariable("s_code") String s_code, HttpSession session) throws JsonProcessingException {
        String id = (String) session.getAttribute("id");

        id = "104539211393038791047";
        s_code = "423XDF";

        System.out.println("세션값 확인" + " id 는 " + id + " 스터디 코드는 " + s_code);

        //01 만약 s_code가 없는 url이면 에러 페이지를 뜬다.
        int findStudyCode = studyService.findStudyCode(s_code);

        if(findStudyCode == -1){
            // 페이지 에러로 다시 home으로
            return "redirect:/pf";
        }

        //불러오는 데이터 목록



        //  1. 스터디 명, 스터디 코드, 스터디 비밀번호 2. 남은 스터디 일 수 * 스터디 인원
        model.addAttribute("getStudyName", studyService.getStudyName(s_code));
        model.addAttribute("getTotalMember", studyService.getTotalMember(s_code));
        model.addAttribute("getStudyMember", studyService.getStudyMember(s_code));

        model.addAttribute("getDday",studyService.getDday(s_code));
        model.addAttribute("getStudyMember", studyService.getStudyMember(s_code));

        //03 스터디 남은 일 수 계산기
        model.addAttribute("s_code", s_code); // 스터디 코드를 모델에 추가
        model.addAttribute("id",id);


        //포도농사 칸

        //월 표기
        String getMonthName = getMonthName();
        model.addAttribute("getMonthName", getMonthName);

        //해당 월 날짜
        int DayCheck = DayCheck();
        System.out.println("이번달" + DayCheck);
        model.addAttribute("DayCheck", DayCheck);

        //날짜마다 문제 푼 갯수 파악
        //스터디 멤버를 가져오고, 멤버의 id를 가지고 와서
        //getSolvedList로 가지고 온다
        model.addAttribute("getStudyMemberID", studyService.getStudyMemberID(s_code));
        System.out.println(studyService.getStudyMemberID(s_code));
        List<String> memberID = (List<String>) studyService.getStudyMemberID(s_code);
        int index = memberID.size();

        //멤버마다 푼 문제를 확인
        for (int i = 0 ; i < index ; i++ ){
            ArrayList<Map<String, String>> solvedList;
            solvedList = codeService.getSolvedByDayCurrentMonth(memberID.get(i));

            //solved 값만 가져온다
            //1. DayCheck만큼 배열을 생성합니다.
            //for 루프를 돌면서, C_DATE 값이 없으면 SUBSTRING으로 날짜를 추출하여 배열에다가 값을 더합니다

            int [] solvedMonth = new int[DayCheck];
            for (Map<String, String> map : solvedList) {
                String dataDay = map.get("C_DATE");
                int day = Integer.parseInt(dataDay.substring(3,5));
                String solved = String.valueOf(map.get("SOLVED"));
                solvedMonth[day-1] = Integer.parseInt(solvedMonth[day-1] + solved);
            }


            ObjectMapper objectMapper = new ObjectMapper();

            String[] solvedDataTypeString = new String[DayCheck];
            for (int j = 0; j < DayCheck; j++) {
                switch (solvedMonth[j]) {
                    case 0:
                        solvedDataTypeString[j] = "solved-0";
                        break;
                    case 1:
                        solvedDataTypeString[j] = "solved-1";
                        break;
                    case 2:
                        solvedDataTypeString[j] = "solved-2";
                        break;
                    case 3:
                        solvedDataTypeString[j] = "solved-3";
                        break;
                    default:
                        solvedDataTypeString[j] = "solved-4";
                        break;
                }
            }

            String solvedDataConvertJson = objectMapper.writeValueAsString(solvedDataTypeString);
            System.out.println(solvedDataConvertJson + "JSON 변환 확인");
            model.addAttribute("solvedData",solvedDataTypeString);
        }

        return "ver4/main";
    }


    //날짜 입력란. 이번달의 일수를 확인
    public int DayCheck(){
        LocalDate currentDate = LocalDate.now();
        YearMonth yearMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonth());
        int daysInMonth = yearMonth.lengthOfMonth();

        System.out.println("현재 " + currentDate.getMonthValue() + "월은 " + daysInMonth + "일입니다.");

        return daysInMonth;
    }

    public String getMonthName() {
        LocalDate currentDate = LocalDate.now();
        YearMonth yearMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonth());
        String monthName = yearMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        return monthName;
    }
}