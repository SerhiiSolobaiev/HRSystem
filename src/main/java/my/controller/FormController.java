package my.controller;

import my.entity.CandidateAnswer;
import my.entity.Question;
import my.entity.Student;
import my.service.CandidateAnswerService;
import my.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
public class FormController {

    /*
  GET http://localhost:8080/student/fill_form :
  [{
    "id": 1,
    "caption": "Оберіть рівень володіння англійською мовою:",
    "typeId": 0,
    "typeValue": "combobox",
    "additionId": 0,
    "additionValue": null,
    "courseId": 0,
    "orderNumber": 0,
    "additionValueArray": [
      "Beginner",
      "Elementary",
      "Pre-Intermediate",
      "Low Intermediate",
      "Intermediate",
      "Upper Intermediate",
      "Pre-advanced",
      "Advanced",
      "Very Advanced"
    ],
    "mandatory": true
  },
  {
    "id": 2,
    "caption": "З якими мовами програмування ви знайомі?",
    "typeId": 0,
    "typeValue": "checkbox",
    "additionId": 0,
    "additionValue": null,
    "courseId": 0,
    "orderNumber": 0,
    "additionValueArray": [
      "Java",
      "C",
      "C++",
      "C#",
      "Pascal"
    ],
    "mandatory": false
  },
  ...

  GET http://localhost:8080/student/get_all_answers
  {
    "id": 100,
    "answers": {
      "1": "111111111111111111111111111111111",
      "2": "dsfdsf",
      "3": "1",
      "4": "2",
      "5": "1"
    }
  },
  {
    "id": 101,
    "answers": {
      "1": "hghgfgh",
      "2": "gfh",
      "3": "fg",
      "4": "101",
      "5": "answer34saaaaaaaaaaaaaaaaaaaaa"
    }
  }
]

POST http://localhost:8080/student/fill_form : - записівает ответы в табличку
body:{
    "id": 300,
    "answers": {
      "1": "1234567890",
      "2": "dsfdsf",
      "3": "1",
      "4": "2",
      "5": "1"
    }}


PUT http://localhost:8080/student/fill_form - делает update (редактирование данных анкеты)
body:{
    "id": 300,
    "answers": {
      "1": "1234567890",
      "2": "dsfdsf",
      "3": "1",
      "4": "2",
      "5": "1"
    }}
     */


    @Autowired
    QuestionService questionService;

    @Autowired
    CandidateAnswerService candidateAnswerService;

    /**
     * Get all questions for form
     */
    @RequestMapping(value = "/student/fill_form", method = RequestMethod.GET)
    public ResponseEntity<List<Question>> getListQuestions() {

        List<Question> questions = questionService.getAllQuestions();
        if (questions.isEmpty()) {
            return new ResponseEntity<List<Question>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
    }

    /**
     * Get all answers of all candidates (for some statistic=) )
     *
     * @return List of all answers of all candidates
     */
    @RequestMapping(value = "/student/get_all_answers", method = RequestMethod.GET)
    public ResponseEntity<List<CandidateAnswer>> getAllAnswers() {
        List<CandidateAnswer> candidateAnswer = candidateAnswerService.getAllAnswers();
        return new ResponseEntity<>(candidateAnswer, HttpStatus.OK);
    }

    /**
     * Save candidate answers
     *
     * @param answers List of candidate answers
     * @return Status of the operation
     */
    @RequestMapping(value = "/student/fill_form", method = RequestMethod.POST)
    public ResponseEntity<Void> saveCandidateForm(@RequestBody CandidateAnswer answers) {


        //TODO
        //THINK ABOUT ID_CANDIDATE and ID_USER
        if (candidateAnswerService.isCandidateAnswersExist(answers.getId())) {
            /**
             * Student already filled the form
             *
             */
            //TODO:
            //THINK WHAT TO RETURN
            System.out.println("A Student with id " + answers.getId() + " already filled the form");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        candidateAnswerService.saveCandidateAnswers(answers);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Update student answers
     */
    @RequestMapping(value = "/student/fill_form/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CandidateAnswer> updateStudentAnswers(@PathVariable("id") int id,
                                                                @RequestBody CandidateAnswer answers) {

        CandidateAnswer candidateAnswer = candidateAnswerService.findById(id);

        //OR THIS:    ??????????????????????????????????
        //CandidateAnswer candidateAnswer = candidateAnswerService.findById(answers.getId());

        if (candidateAnswer == null) {
            System.out.println("Student with id " + id + " not found OR he is not yet filled the form");
            return new ResponseEntity<CandidateAnswer>(HttpStatus.NOT_FOUND);
        }

        candidateAnswer.setAnswers(answers.getAnswers());

        candidateAnswerService.updateCandidateAnswer(candidateAnswer);
        return new ResponseEntity<CandidateAnswer>(candidateAnswer, HttpStatus.OK);
    }
}
