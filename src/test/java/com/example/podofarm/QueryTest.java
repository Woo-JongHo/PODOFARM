package com.example.podofarm;

import com.example.podofarm.vo.StudyVO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class QueryTest {

    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE"; // 데이터베이스 URL에 맞게 수정
    private static final String DB_USER = "c##pf";
    private static final String DB_PASSWORD = "1234";

    private Connection connection;

    public QueryTest() throws SQLException {
        connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public void insertStudy(StudyVO study) throws SQLException {
        // INSERT 문의 '?'에 해당하는 값을 설정하기 위해 PreparedStatement 사용
        String insertQuery = "INSERT INTO STUDY (S_NO, USERNO, S_CODE, S_NAME, S_START, S_END) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            // 값 설정
            statement.setInt(1, study.getS_no());
            statement.setInt(2, study.getUserNo());
            statement.setString(3, study.getS_code());
            statement.setString(4, study.getS_name());
            statement.setDate(5, (Date) study.getS_start());
            statement.setDate(6, (Date) study.getS_end());

            // 쿼리 실행
            statement.executeUpdate();
        }
    }

    public static void main(String[] args) {
        try {
            QueryTest test = new QueryTest();

            // 테스트 데이터
            StudyVO study = new StudyVO();
            study.setS_no(1);
            study.setUserNo(1);
            study.setS_code("4X321D");
            study.setS_name("포도팜 잘해보자");
            study.setS_start(new Date(System.currentTimeMillis())); // 여기에 적절한 날짜를 설정
            study.setS_end(new Date(System.currentTimeMillis()));   // 여기에 적절한 날짜를 설정

            // 테스트 실행
            test.insertStudy(study);
            System.out.println("삽입 완료");

            // 연결 종료
            test.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
