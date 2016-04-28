package my.service;

import my.ConnectionFactory;
import my.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    private Connection connection;
    private PreparedStatement statement;

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Question> getAllQuestions() {
//
//        jdbcTemplate = new JdbcTemplate(dataSource);
//
//        //TODO:
//        //hr_system.question_course_maps.course_id MUST BE INSERTED BY ADMIN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        //1 - (for testing) from table hr_system.question_course_maps
        int courseID = 1;

        String query = " SELECT hr_system.question.id, hr_system.question.caption, hr_system.question.type_id, " +
                "hr_system.question.is_mandatory, hr_system.type.value, hr_system.question_course_maps.course_id, " +
                "hr_system.question_course_maps.order_number\n" +
                "FROM hr_system.question\n" +
                "JOIN hr_system.type\n" +
                "ON hr_system.question.type_id = hr_system.type.id\n" +
                "JOIN hr_system.question_course_maps\n" +
                "ON hr_system.question.id = hr_system.question_course_maps.question_id\n" +
                "WHERE hr_system.question_course_maps.course_id = " + courseID + "\n" +
                "ORDER BY hr_system.question_course_maps.order_number";


        ResultSet rs = null;
        List<Question> questions = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();

            while (rs.next()) {
                int idQuestion = rs.getInt("id");
                String caption = rs.getString("caption");
                int idType = rs.getInt("type_id");
                boolean isMandatory = rs.getBoolean("is_mandatory");
                String typeValue = rs.getString("value");
                int questionCourseID = rs.getInt("course_id");
                int orderNumber = rs.getInt("order_number");
                List<String> additionValueArray = getQuestionAdditionValue(idQuestion);


                questions.add(new Question(idQuestion, caption, isMandatory, idType, typeValue,
                    additionValueArray, questionCourseID, orderNumber));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return questions;
//        List<Question> questions = new ArrayList<>();
//        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
//
//        for (Map row : rows) {
//            int idQuestion = (int) row.get("id");
//            String caption = row.get("caption").toString();
//            int idType = (int) row.get("type_id");
//            boolean isMandatory = (boolean) row.get("is_mandatory");
//            String typeValue = row.get("value").toString();
//            int questionCourseID = (int) row.get("course_id");
//            int orderNumber = (int) row.get("order_number");
//
//            List<String> additionValueArray = getQuestionAdditionValue(idQuestion);
//
//            questions.add(new Question(idQuestion, caption, isMandatory, idType, typeValue,
//                    additionValueArray, questionCourseID, orderNumber));
//        }
//
//        return questions;
    }

    private List<String> getQuestionAdditionValue(int id) {

        String query = " SELECT hr_system.question_addition.value\n" +
                " FROM hr_system.question_addition\n" +
                " WHERE hr_system.question_addition.question_id = " + id;
        ResultSet rs = null;
        List<String> additionValueArray = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();

            while (rs.next()) {
                String caption = rs.getString("value");
                additionValueArray.add(caption);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return additionValueArray;





//        jdbcTemplate = new JdbcTemplate(dataSource);
//
//        String query = " SELECT hr_system.question_addition.value\n" +
//                " FROM hr_system.question_addition\n" +
//                " WHERE hr_system.question_addition.question_id = " + id;
//
//        List<String> additionValueArray = new ArrayList<>();
//        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
//
//        for (Map row : rows) {
//            String caption = row.get("value").toString();
//            additionValueArray.add(caption);
//        }
//
//        return additionValueArray;
    }
}
