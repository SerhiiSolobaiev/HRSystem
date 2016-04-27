package my.service;

import my.ConnectionFactory;
import my.entity.Question;
import my.entity.Student;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService{

    private Connection connection;
    private PreparedStatement statement;

    @Override
    public List<Question> getAllQuestions() {
        String query = " SELECT hr_system.question.id, hr_system.question.caption, hr_system.question.is_mandatory, hr_system.type.value\n" +
                " FROM hr_system.question\n" +
                " JOIN hr_system.type\n" +
                " ON hr_system.question.type_id = hr_system.type.id";
        ResultSet rs = null;
        List<Question> questions = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();

            while (rs.next()) {
                int idQuestion= rs.getInt("id");
                String caption = rs.getString("caption");
                boolean isMandatory= rs.getBoolean("is_mandatory");
                String typeValue = rs.getString("value");
                List<String> additionValueArray = getQuestionAdditionValue(idQuestion);

                questions.add(new Question(idQuestion, caption, isMandatory, typeValue, additionValueArray));
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
    }

    private List<String> getQuestionAdditionValue(int id){
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
    }
}


/*

 SELECT hr_system.question.id, hr_system.question.caption, hr_system.question.is_mandatory, hr_system.type.value, hr_system.question_addition.value
 FROM hr_system.question
 JOIN hr_system.type
 ON hr_system.question.type_id = hr_system.type.id
 LEFT JOIN hr_system.question_addition
 ON hr_system.question.id = hr_system.question_addition.question_id





 */