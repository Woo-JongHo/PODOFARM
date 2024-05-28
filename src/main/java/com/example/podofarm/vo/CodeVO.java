package com.example.podofarm.vo;

import java.util.Date;

public class CodeVO {

    //CodeVO는 백준허브의 데이터 받는 것을 참조하여 만들었습니다.
    //extension에서 받아오는 데이터 값들을 분류하였습니다.


    //문제번호, 문제, 레벨, 문제설명, 링크, 언어종류, 답안, 결과, 실행시간, 메모리


    private int c_no;

    private String id;

    private String c_filename;

    private String c_source;

    private String c_readme;

    private String c_level;
    public Date c_date;

    public int getC_no() {
        return c_no;
    }


    public void setC_no(int c_no) {
        this.c_no = c_no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getC_filename() {
        return c_filename;
    }

    public void setC_filename(String c_filename) {
        this.c_filename = c_filename;
    }

    public String getC_source() {
        return c_source;
    }

    public void setC_source(String c_source) {
        this.c_source = c_source;
    }

    public String getC_readme() {
        return c_readme;
    }

    public void setC_readme(String c_readme) {
        this.c_readme = c_readme;
    }

    public String getC_level() {
        return c_level;
    }

    public void setC_level(String c_level) {
        this.c_level = c_level;
    }

    public int getC_like() {
        return c_like;
    }

    public void setC_like(int c_like) {
        this.c_like = c_like;
    }

    public Date getC_date() {
        return c_date;
    }

    public void setC_date(Date c_date) {
        this.c_date = c_date;
    }

    public int getC_time() {
        return c_time;
    }

    public void setC_time(int c_time) {
        this.c_time = c_time;
    }

    private int c_like;


    //타임 기능 구현시 이용할 것, DB에도 설정안한 상태
    private int c_time;

}
