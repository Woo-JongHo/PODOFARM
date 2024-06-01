package com.example.podofarm.controller;


import com.example.podofarm.service.CodeService;
import com.example.podofarm.service.StudyService;
import com.example.podofarm.service.UserService;
import com.example.podofarm.vo.CodeVO;
import com.example.podofarm.vo.StudyVO;
import com.example.podofarm.vo.UploadRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.*;


@Controller
@Setter
public class PersonalController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudyService studyService;

    @Autowired
    private CodeService codeService;

    //스터디코드/이메일로 할 것

    //personal로 변경할 것
    @CrossOrigin(origins = "https://school.programmers.co.kr")
    @RequestMapping(method = RequestMethod.GET, value = "/ps")
    public String personal(Model model, HttpSession session) {
        //String id = (String) session.getAttribute("id");
        String id = "1234";
        String s_code = "423XDF";

        model.addAttribute("getStudyMember", studyService.getStudyMember(s_code));
        model.addAttribute("getName", userService.getName(id));
        model.addAttribute("getTotalSolvedById", codeService.getTotalSolvedById(id));
        model.addAttribute("getSolvedTitle" , codeService.getSolvedTitleDESC(id));
        //model.addAttribute("getSolvedTitle" , codeService.getSolvedTitleASC(id));

        List<String> solvedTitles = codeService.getSolvedTitleDESC(id);
        for (String title : solvedTitles) {
            System.out.println(title);
        }
        //익스텐션에서 푼 데이터 가져오기
        return "ver4/personal";
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(method = RequestMethod.POST, value = "/ps")
    public ResponseEntity<String> upload(@RequestBody UploadRequest request) throws ParseException {
        CodeVO code = new CodeVO();

        // 요청에서 필요한 데이터 추출
        String id = request.getId();
        String filename = request.getFilename();
        int filenamelength = filename.length();
        filename = filename.substring(0,filenamelength-5);

        String sourceText = request.getSourceText();
        String readmeText = request.getReadmeText();
        String commitMessage = request.getCommitMessage();
        String dateInfo = request.getDateInfo();

        System.out.println(dateInfo);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(dateInfo);


        //CODE 인서트하기
        code.setId(id);
        code.setC_filename(filename);
        code.setC_source(sourceText);
        code.setC_readme(readmeText);
        code.setC_like(0);
        code.setC_date(date);

        /*
        System.out.println(id);
        System.out.println(sourceText);
        System.out.println(readmeText);
        System.out.println(filename);
        System.out.println(commitMessage);
        System.out.println(date);
        */

        int insertCode = codeService.insertCode(code);





        // 응답 생성
        return ResponseEntity.ok("Upload successful"); // 성공적으로 처리되었을 경우 응답
    }

    /*오류코드
    *     @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/ps")
    public ResponseEntity<String> upload(@RequestBody UploadRequest request) {
        // 요청에서 필요한 데이터 추출
        String id = request.getId();
        String studyCode = request.getStudyCode();
        String sourceText = request.getSourceText();
        String readmeText = request.getReadmeText();
        String filename = request.getFilename();
        String commitMessage = request.getCommitMessage();

        // 요청 처리 로직 추가
        // 예를 들어, 서비스를 호출하거나 데이터베이스에 저장하는 등의 작업을 수행할 수 있습니다.

        // 응답 생성
        return ResponseEntity.ok("Upload successful"); // 성공적으로 처리되었을 경우 응답
    }*/
}


