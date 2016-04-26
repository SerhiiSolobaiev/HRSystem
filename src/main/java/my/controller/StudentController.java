package my.controller;

import my.entity.Student;
import my.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;  //Service which will do all data retrieval/manipulation work

    //-------------------Retrieve All Students--------------------------------------------------------

    @RequestMapping(value = "/student/", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> listAllStudents() {




        List<Student> students = studentService.findAllStudents();
        if(students.isEmpty()){
            return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

    //-------------------Retrieve Single Student--------------------------------------------------------

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudent(@PathVariable("id") long id) {
        System.out.println("Fetching Student with id " + id);
        Student student = studentService.findById(id);
        if (student == null) {
            System.out.println("Student with id " + id + " not found");
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }



    //-------------------Create a Student--------------------------------------------------------

    @RequestMapping(value = "/student/", method = RequestMethod.POST)
    public ResponseEntity<Void> createStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Student " + student.getName());

        if (studentService.isStudentExist(student)) {
            System.out.println("A Student with name " + student.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        studentService.saveStudent(student);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/student/{id}").buildAndExpand(student.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    //------------------- Update a Student --------------------------------------------------------

    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
        System.out.println("Updating Student " + id);

        Student currentStudent = studentService.findById(id);

        if (currentStudent==null) {
            System.out.println("Student with id " + id + " not found");
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }

        currentStudent.setName(student.getName());
        currentStudent.setEmail(student.getEmail());
        currentStudent.setPassword(student.getPassword());
        currentStudent.setPatronymic(student.getPatronymic());
        currentStudent.setSurname(student.getSurname());

        studentService.updateStudent(currentStudent);
        return new ResponseEntity<Student>(currentStudent, HttpStatus.OK);
    }

    //------------------- Delete a Student --------------------------------------------------------

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Student with id " + id);

        Student student = studentService.findById(id);
        if (student == null) {
            System.out.println("Unable to delete. Student with id " + id + " not found");
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }

        studentService.deleteStudentById(id);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }


    //------------------- Delete All Students --------------------------------------------------------

    @RequestMapping(value = "/student/", method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteAllStudents() {
        System.out.println("Deleting All Students");

        studentService.deleteAllStudents();
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }
}
