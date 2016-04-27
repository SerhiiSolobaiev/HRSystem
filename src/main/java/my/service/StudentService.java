package my.service;


import my.entity.Student;

import java.util.List;

public interface StudentService {

    Student findById(long id);

    void saveStudent(Student user);

    void updateStudent(Student user);

    void deleteStudentById(long id);

    List<Student> findAllStudents();

    void deleteAllStudents();

    boolean isStudentExist(Student user);

}