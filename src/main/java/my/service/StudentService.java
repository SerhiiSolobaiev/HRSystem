package my.service;


import my.entity.Student;

import java.util.List;

public interface StudentService {

    Student findById(long id);

    Student findByName(String name);

    void saveStudent(Student user);

    void updateStudent(Student user);

    void deleteStudentById(long id);

    List<Student> findAllStudents();

    void deleteAllStudents();

    public boolean isStudentExist(Student user);

}