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
    @GetMapping("/ps")
    public String personal(HttpSession session){
        String id = (String) session.getAttribute("id");

        return "ver4/personal";
    }

}