package com.example.podofarm.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "chrome-extension://ghbibjdmcondjdiebninoidgihdklndj")
public class CodeController {

    @PostMapping("/receive-code")
    public String receiveCode(@RequestBody String code) {
        // 익스텐션으로부터 받은 코드를 처리하는 로직을 작성


        System.out.println("Received code from extension: " + code);



        // 응답을 반환할 수도 있음
        return "Code received successfully";
    }
}
