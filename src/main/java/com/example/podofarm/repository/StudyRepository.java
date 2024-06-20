package com.example.podofarm.repository;

import com.example.podofarm.db.StudyDBManger;
import com.example.podofarm.vo.StudyVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class StudyRepository {


    public int createStudy(StudyVO s) {

        return StudyDBManger.createStudy(s);
    }

    public int updateStudyLeader(String id) {
        return StudyDBManger.updateStudyLeader(id);
    }

    public String checkStudyCode(String id) {
        return StudyDBManger.checkStudyCode(id);
    }

    public String getStudyCode(String id){
        return StudyDBManger.getStudyCode(id);
    }

    public int findStudyCode(String s_code) {
        return StudyDBManger.findStudyCode(s_code);
    }

    public String getStudyName(String s_code) {
        return StudyDBManger.getStudyName(s_code);
    }

    public Object getStudyMember(String s_code) {
        return StudyDBManger.getStudyMember(s_code);
    }

    public int getTotalMember(String s_code) {
        return StudyDBManger.getTotalMember(s_code);
    }

    public Object getDday(String s_code) {
        return StudyDBManger.getDday(s_code);
    }

    public Object getStudyMemberIdByMonth(String s_code, String s_start){
        return StudyDBManger.getStudyMemberIdByMonth(s_code,s_start);
    }

    public List<Map<String, String>> getRecentActivity(String s_code) {
        return StudyDBManger.getRecentActivity(s_code);
    }

    public List<Map<String, String>> getSolvedRank(String s_code) {
        return StudyDBManger.getSolvedRank(s_code);
    }

    public String getStartDay(String s_code) {
        return StudyDBManger.getStartDay(s_code);
    }

    public Object getStudyMemberByMonth(String s_code, String s_start) {
        return StudyDBManger.getStudyMemberByMonth(s_code,s_start);
    }

    public List<String> getStudyMemberId(String s_code) {
        return StudyDBManger.getStudyMemberId(s_code);
    }
}
