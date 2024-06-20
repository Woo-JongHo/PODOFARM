package com.example.podofarm.repository;

import com.example.podofarm.db.CodeDBManger;
import com.example.podofarm.vo.CodeVO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CodeRepository {

    public static int pageSize =10;
    public static int totalRecord;
    public static int totalPage;

    public int syncRepo(String sCode) {

        return CodeDBManger.syncRepo(sCode);
    }

    public ArrayList<Map<String, String>> getSolvedByDaySelectedMonth(String id, String SelectMonth) {
        return CodeDBManger.getSolvedByDaySelectedMonth(id, SelectMonth);
    }

    public int getTotalSolvedById(String id) {
        return CodeDBManger.getTotalSolvedById(id);
    }

    public int insertCode(CodeVO code) {
        return CodeDBManger.insertCode(code);
    }

    public List<CodeVO> getSolvedCode(String id) {
        totalRecord = CodeDBManger.getTotalSolvedById(id);
        totalPage = (int)Math.ceil(totalRecord / (double)pageSize);
        return CodeDBManger.getSolvedCode(id);
    }


    public CodeVO getCodeByProblemId(String problemId) {
        return CodeDBManger.getCodeByProblemId(problemId);
    }


}
