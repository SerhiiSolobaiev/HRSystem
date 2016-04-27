package my.service;

import my.ConnectionFactory;
import my.entity.Student;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    private static final AtomicLong counter = new AtomicLong();

    private Connection connection;
    private PreparedStatement statement;

    public List<Student> findAllStudents() {
        String query = "SELECT * FROM hr_system.users";
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();

            while (rs.next()) {
                long idStudent = rs.getLong("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String patronymic = rs.getString("patronymic");

                students.add(new Student(idStudent, email, password, name, surname, patronymic));
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
        return students;
    }

    public Student findById(long id) {
        String query = "SELECT * FROM hr_system.users WHERE id = " + id;
        ResultSet rs = null;
        Student student = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();

            if (rs.next()) {
                long idStudent = rs.getLong("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String patronymic = rs.getString("patronymic");

                student = new Student(idStudent, email, password, name, surname, patronymic);
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
        return student;
    }

    public void saveStudent(Student student) {
        String query = "INSERT INTO hr_system.users (email, password, name, surname, patronymic) VALUES (lower(?),?,?,?,?)";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            statement.setString(1, student.getEmail());
            statement.setString(2, student.getPassword());
            statement.setString(3, student.getName());
            statement.setString(4, student.getSurname());
            statement.setString(5, student.getPatronymic());

            statement.execute();
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

    public void updateStudent(Student student) {
        String query = "UPDATE hr_system.users SET email = lower(?),password = ?,name = ?,surname = ?, patronymic = ? " +
                "WHERE id = " + student.getId();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            statement.setString(1, student.getEmail());
            statement.setString(2, student.getPassword());
            statement.setString(3, student.getName());
            statement.setString(4, student.getSurname());
            statement.setString(5, student.getPatronymic());

            statement.execute();
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

    public void deleteStudentById(long id) {
        /*
        not necessary
         */
    }

    public boolean isStudentExist(Student student) {
        return findById(student.getId()) != null;
    }

    public void deleteAllStudents() {
        /*
        not necessary
         */
    }

}
