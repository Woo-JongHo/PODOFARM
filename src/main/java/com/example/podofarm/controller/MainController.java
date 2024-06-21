package com.example.podofarm.controller;


import com.example.podofarm.service.CodeService;
import com.example.podofarm.service.StudyService;
import com.example.podofarm.service.UserService;
import com.example.podofarm.vo.StudyVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupdocs.conversion.internal.c.a.w.internal.Se;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
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
    int DayCheck = DayCheck();
    LocalDate currentDate = LocalDate.now();
    String selectMonth = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));


    @GetMapping("/main")
    public String main(HttpSession session){
        String id = (String) session.getAttribute("id");

        return "ver4/main2";
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
    public String studyMainPage(Model model,
                                @PathVariable("s_code") String s_code,
                                HttpSession session) throws JsonProcessingException {
        String id = (String) session.getAttribute("id");


        //TEST CASE
        id = "104539211393038791047";
        s_code = "423XDF";
        String s_start= studyService.getStartDay(s_code);
        System.out.print(s_start + "start 봐바");

        //01 만약 s_code가 없는 url이면 에러 페이지를 뜬다.
        int findStudyCode = studyService.findStudyCode(s_code);

        if(findStudyCode == -1)
            return "redirect:/pf";

        //불러오는 데이터 목록
        //  1. 스터디 명, 스터디 코드, 스터디 비밀번호 2. 남은 스터디 일 수 * 스터디 인원
        model.addAttribute("getStudyName", studyService.getStudyName(s_code));
        model.addAttribute("getTotalMember", studyService.getTotalMember(s_code));
        model.addAttribute("getStudyMember", studyService.getStudyMember(s_code));
        model.addAttribute("getDday",studyService.getDday(s_code));
        //03 스터디 남은 일 수 계산기
        model.addAttribute("s_code", s_code); // 스터디 코드를 모델에 추가
        model.addAttribute("id",id);

        //04 최근활동  업데이트
        model.addAttribute("activity",studyService.getRecentActivity(s_code));

        //포도농사 칸
        model.addAttribute("rank",studyService.getSolvedRank(s_code));


        //월 표기
        String getMonthName = getMonthName();
        model.addAttribute("getMonthName", getMonthName);

        //해당 월 날짜


        Podofarm(s_code,s_start, model);



        return "ver4/main";
    }



    public List<String> CalcDate(String s_start, Model model) {
        int s_years = Integer.parseInt(s_start.substring(0, 4));
        int s_month = Integer.parseInt(s_start.substring(5));

        int c_years = currentDate.getYear();
        int c_month = currentDate.getMonthValue();

        int yearDiff = c_years - s_years;
        int monthDiff;
        if (yearDiff == 0) {
            monthDiff = c_month - s_month;
        } else {
            monthDiff = (yearDiff * 12) + (c_month - s_month);
        }

        List<String> monthList = new ArrayList<>();

        for (int i = 0; i <= monthDiff; i++) {
            monthList.add(s_start);

            String[] parts = s_start.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);

            month = month + 1;
            if (month > 12) {
                year++;
                month = 1;
            }

            String monthString = String.format("%02d", month);
            String yearString = String.valueOf(year);

            s_start = yearString + "-" + monthString;
            model.addAttribute("monthList", monthList);
        }

        return monthList;
    }
    private int getLastDayOfMonth(int year, int month) {
        // 각 월의 마지막 날을 반환합니다.
        return java.time.YearMonth.of(year, month).lengthOfMonth();
    }

    //날짜 입력란. 이번달의 일수를 확인
    public int DayCheck(){
        LocalDate currentDate = LocalDate.now();
        YearMonth yearMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonth());
        int daysInMonth = yearMonth.lengthOfMonth();

        System.out.println("현재 " + currentDate.getMonthValue() + "월은 " + daysInMonth + "일입니다.");

        return daysInMonth;
    }
    public int DayCheck(String month){
        YearMonth yearMonth = YearMonth.parse(month);
        int daysInMonth = yearMonth.lengthOfMonth();
        System.out.println("해당 " + yearMonth.getYear() + "년 " + yearMonth.getMonthValue() + "월은 " + daysInMonth + "일입니다.");

        return daysInMonth;
    }

    public String getMonthName() {
        LocalDate currentDate = LocalDate.now();
        YearMonth yearMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonth());
        String monthName = yearMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        return monthName;
    }



    //01 STUDY에 생성일자를 가지고 와서 몇 개월동안 지속되었는지확인
    //02 month에 따라, Day 설정
    //03 month에 따라, 인원수 확인 및, 인원수가 푼 문제 확인
    //04 삼중배열 도전 [yyyy-mm][멤버][dd의 푼 문제]
     public void Podofarm(String s_code, String s_start, Model model) {

        List<String> Month = CalcDate(s_start,model);
        int Members;
        int DayCheck;

        for( String month : Month){
            List<String> memberID = (List<String>)  studyService.getStudyMemberIdByMonth(s_code,month);
            List<String> memberName = (List<String>) studyService.getStudyMemberByMonth(s_code,month);

            // '2024-03' 에 멤버 사이즈는 N명
            int MonthMember = memberID.size();



            //멤버마다 푼 문제를 리스트가져옵니다
            int [] MonthDay = new int [DayCheck(month)];
            System.out.println (MonthDay + "이번달은 몇일까지있는가");

            //후에 달마다 기본적으로 날짜를 세주는 칸을 생성하기 위해서
            model.addAttribute(month+"-DayCheck" , DayCheck(month));

            System.out.println(month +"-Members" +  memberName + "가지고온 멤버이름들");
            model.addAttribute(month+"_Members", memberName);

            for(int i = 0 ;  i < MonthMember ; i++){
                ArrayList<Map<String, String>> solvedList;
                solvedList = codeService.getSolvedByDaySelectedMonth(memberID.get(i), month);

                for (Map<String, String> map : solvedList) {
                    String dataDay = map.get("C_DATE");
                    int day = Integer.parseInt(dataDay.substring(8, 10)); // 일(day)을 가져와야 하므로 8, 10 인덱스 사용
                    String solved = String.valueOf(map.get("SOLVED"));
                    MonthDay[day - 1] += Integer.parseInt(solved); // 합산하기 위해 += 사용

                }
            }

        }

        model.addAttribute("month",Month);



        /*    List<String> monthList = CalcDate(s_start, model);

        for (String month : monthList) {
            List<String> memberID = (List<String>) studyService.getStudyMemberIdByMonth(s_code, month);

            int index = memberID.size();

            for (int i = 0; i < index; i++) {
                ArrayList<Map<String, String>> solvedList;
                solvedList = codeService.getSolvedByDaySelectedMonth(memberID.get(i), month);
                int[] solvedMonth = new int[DayCheck(month)];
                model.addAttribute("DayCheck", DayCheck(month));

                for (Map<String, String> map : solvedList) {
                    String dataDay = map.get("C_DATE");
                    int day = Integer.parseInt(dataDay.substring(8, 10)); // 일(day)을 가져와야 하므로 8, 10 인덱스 사용
                    String solved = String.valueOf(map.get("SOLVED"));
                    solvedMonth[day - 1] += Integer.parseInt(solved); // 합산하기 위해 += 사용
                }

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
                model.addAttribute(month + "-solvedData_" + i, solvedDataTypeString);
            }

            String[] arr = new String[index];
            for (int k = 0; k < index; k++) {
                arr[k] = month + "-solvedData_" + k;
            }
            model.addAttribute(month + "-solvedData", arr); // month별 solvedData를 저장
                model.addAttribute("getStudyMemberByMonth", studyService.getStudyMemberByMonth(s_code,month));
        }
        model.addAttribute("monthList", monthList); // monthList를 모델에 추가
            */
    }

}