package my.service;

import my.ConnectionFactory;
import my.entity.CandidateAnswer;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service("candidateAnswerService")
public class CandidateAnswerServiceImpl implements CandidateAnswerService {

    private Connection connection;
    private PreparedStatement statement;

    @Override
    public void saveCandidateAnswers(CandidateAnswer answers) {

        try {
            connection = ConnectionFactory.getConnection();
            for (Map.Entry e : answers.getAnswers().entrySet()) {
                String query = "INSERT INTO hr_system.candidate_answer (candidate_id, question_id, value) " +
                        "VALUES (?,?,?)";
                statement = connection.prepareStatement(query);

                System.out.println("answers.getId() = " + answers.getId() + " : " + e.getKey() + " " + e.getValue());

                statement.setInt(1, answers.getId());
                statement.setInt(2, (Integer) e.getKey());
                statement.setString(3, String.valueOf(e.getValue()));

                statement.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isCandidateAnswersExist(int idCandidate) {
        String query = "SELECT * FROM hr_system.candidate_answer WHERE candidate_id = " + idCandidate;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();

            if (rs.next()) {
                return true;
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
        return false;
    }

    @Override
    public List<CandidateAnswer> getAllAnswers() {

        List<CandidateAnswer> allAnswers = new ArrayList<>();
        String query = "SELECT * FROM hr_system.candidate_answer";
        ResultSet rs = null;
//        try {
//            connection = ConnectionFactory.getConnection();
//            statement = connection.prepareStatement(query);
//            rs = statement.executeQuery();
//
//            while (rs.next()) {
//                long idStudent = rs.getLong("id");
//                String email = rs.getString("email");
//                String name = rs.getString("name");
//                String surname = rs.getString("surname");
//                String patronymic = rs.getString("patronymic");
//
//                allAnswers.add(new CandidateAnswer(idStudent, ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (statement != null) statement.close();
//                if (connection != null) connection.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        return allAnswers;
    }

    @Override
    public CandidateAnswer findById(int id) {
        String query = "SELECT * FROM hr_system.candidate_answer WHERE candidate_id = " + id;
        ResultSet rs = null;
        CandidateAnswer candidateAnswer = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();

            int idCandidateAnswer = id;
            Map<Integer, String> map = new TreeMap<>();

            if (rs.next()) {
                int idQuestion = rs.getInt("question_id");
                String value = rs.getString("value");

                map.put(idQuestion, value);
            }

            candidateAnswer = new CandidateAnswer(idCandidateAnswer, map);
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
        return candidateAnswer;
    }

    @Override
    public void updateCandidateAnswer(CandidateAnswer answers) {

        try {
            connection = ConnectionFactory.getConnection();
            for (Map.Entry e : answers.getAnswers().entrySet()) {
                String query = "UPDATE hr_system.candidate_answer SET value = ?" +
                        "WHERE candidate_id = ? AND question_id = ?";
                statement = connection.prepareStatement(query);

                System.out.println("updateCandidateAnswer === answers.getId() = " + answers.getId() + " : " + e.getKey() + " " + e.getValue());

                int idQuestion = (Integer) e.getKey();
                String value = (String) e.getValue();

                statement.setString(1, value);
                statement.setInt(2, answers.getId());
                statement.setInt(3, idQuestion);

                statement.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
