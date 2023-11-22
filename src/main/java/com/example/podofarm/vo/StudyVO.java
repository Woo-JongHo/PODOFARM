package com.example.podofarm.vo;



import java.util.Date;

public class StudyVO {


    private int s_no;
    private int userNo;

    private String s_code;
    private String s_name;
    private Date s_start;
    private Date s_end;
    private int s_member;

    public int getS_no() {
        return s_no;
    }

    public void setS_no(int s_no) {
        this.s_no = s_no;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getS_code() {
        return s_code;
    }

    public void setS_code(String s_code) {
        this.s_code = s_code;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public Date getS_start() {
        return s_start;
    }

    public void setS_start(Date s_start) {
        this.s_start = s_start;
    }

    public Date getS_end() {
        return s_end;
    }

    public void setS_end(Date s_end) {
        this.s_end = s_end;
    }

    public int getS_member() {
        return s_member;
    }

    public void setS_member(int s_member) {
        this.s_member = s_member;
    }
}
