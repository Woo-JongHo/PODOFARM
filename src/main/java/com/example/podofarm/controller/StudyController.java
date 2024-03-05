package com.example.podofarm.controller;


import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Setter
public class StudyController {



    @GetMapping("study")
    public String study(){
        return "study/nostudy/mainpage";
    }
}
