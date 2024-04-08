package com.example.podofarm.controller;

import com.example.podofarm.service.StudyService;
import com.example.podofarm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CodeController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudyService studyService;

    //연동하기 버튼으로 스터디와 아이디 확인
    // ResponseEntity로 서로 응답 상호작용
    @CrossOrigin(origins = "chrome-extension://ghbibjdmcondjdiebninoidgihdklndj")
    @PostMapping("/receive-code")
    public ResponseEntity<String> receiveCode(@RequestBody String data) {
        try {
            // 받은 JSON 문자열을 JsonNode로 파싱
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(data);

            // 필요한 정보 추출
            String id = jsonNode.get("id").asText();
            String studyCode = jsonNode.get("studyCode").asText();


            // DB에 데이터가 있는지 확인
            String getStudyCode = studyService.getStudyCode(id);

            if (getStudyCode.equals(studyCode)){
                return ResponseEntity.ok("success");
            }else{
                return ResponseEntity.ok("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request");
        }
    }
}
