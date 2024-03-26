package com.example.podofarm.vo;

public class CodeVO {

    //CodeVO는 백준허브의 데이터 받는 것을 참조하여 만들었습니다.
    //extension에서 받아오는 데이터 값들을 분류하였습니다.


    //문제번호, 문제, 레벨, 문제설명, 링크, 언어종류, 답안, 결과, 실행시간, 메모리


    private int c_no;
    private String id;



    private String c_problem_description;
    private String c_problem_id;
    private String c_link;
    private String c_title;

    private String c_level;
    private String c_code;
    private String c_result;
    private String c_runtime;
    private String c_memory;

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

    public String getC_problem_description() {
        return c_problem_description;
    }

    public void setC_problem_description(String c_problem_description) {
        this.c_problem_description = c_problem_description;
    }

    public String getC_problem_id() {
        return c_problem_id;
    }

    public void setC_problem_id(String c_problem_id) {
        this.c_problem_id = c_problem_id;
    }

    public String getC_link() {
        return c_link;
    }

    public void setC_link(String c_link) {
        this.c_link = c_link;
    }

    public String getC_title() {
        return c_title;
    }

    public void setC_title(String c_title) {
        this.c_title = c_title;
    }

    public String getC_level() {
        return c_level;
    }

    public void setC_level(String c_level) {
        this.c_level = c_level;
    }

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }

    public String getC_result() {
        return c_result;
    }
    public void setC_result(String c_result) {
        this.c_result = c_result;
    }

    public String getC_runtime() {
        return c_runtime;
    }

    public void setC_runtime(String c_runtime) {
        this.c_runtime = c_runtime;
    }

    public String getC_memory() {
        return c_memory;
    }

    public void setC_memory(String c_memory) {
        this.c_memory = c_memory;
    }


}
