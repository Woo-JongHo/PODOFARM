package com.example.podofarm.vo;



import java.util.Date;

public class StudyVO {


    private int s_no;


    private String id;

    private String s_code;


    private String s_password;
    private String s_name;
    private String s_start;
    private String s_end;
    private Date s_enter;

    public Date getS_enter() {
        return s_enter;
    }

    public void setS_enter(Date s_enter) {
        this.s_enter = s_enter;
    }


    public int getS_no() {
        return s_no;
    }

    public void setS_no(int s_no) {
        this.s_no = s_no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getS_code() {
        return s_code;
    }

    public void setS_code(String s_code) {
        this.s_code = s_code;
    }

    public String getS_password() {
        return s_password;
    }

    public void setS_password(String s_password) {
        this.s_password = s_password;
    }
    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_start() {
        return s_start;
    }

    public void setS_start(String s_start) {
        this.s_start = s_start;
    }

    public String getS_end() {
        return s_end;
    }

    public void setS_end(String s_end) {
        this.s_end = s_end;
    }


}
