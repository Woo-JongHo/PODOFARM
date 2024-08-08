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
    @PostMapping("/receive-sync")
    public ResponseEntity<String> receiveSync(@RequestBody String data) {
        try {

            /* 연동하기 눌렀을 때 ID, 와 STUDYCODE 확인 후, 연동하는 작업 */
            ObjectMapper sync = new ObjectMapper();
            JsonNode convertSync = sync.readTree(data);

            // 필요한 정보 추출
            String id = convertSync.get("id").asText();
            String studyCode = convertSync.get("studyCode").asText();

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


    /*익스텐션에서 넘어오는 작업이기 때문에 사이트와 구분하여 세션으로 데이터를 저장해서 넘기지 않았음*/
    @CrossOrigin(origins = {"chrome-extension://ghbibjdmcondjdiebninoidgihdklndj", "https://school.programmers.co.kr"})
    @PostMapping("/receive-data")
    public ResponseEntity<String> receiveData(@RequestBody String data) {
        try {

            /* 파싱된 데이터를 옮기는 작업*/
            ObjectMapper Data = new ObjectMapper();
            JsonNode convertData = Data.readTree(data);

            // 필요한 정보 추출
            String id = convertData.get("id").asText();
            String studyCode = convertData.get("studyCode").asText();
            String sourceText = convertData.get("sourceText").asText();
            String readmeText = convertData.get("readmeText").asText();
            String filename = convertData.get("filename").asText();
            String commitMessage = convertData.get("commitMessage").asText();


            /*
            * sourceText - 답안
            * readme - 문제설명
            * filename - 문제명
            * commitMessage - 시간초 매모리.USERNO
            * */

            System.out.println("sourceText는 다음과 같습니다 : " + sourceText);
            System.out.println("readme는 다음과 같습니다 : " + readmeText);
            System.out.println("filename는 다음과 같습니다 : " + filename);
            System.out.println("commitMessage는 다음과 같습니다 : " + commitMessage);


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
