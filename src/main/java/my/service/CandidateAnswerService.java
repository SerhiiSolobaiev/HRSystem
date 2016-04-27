package my.service;

import my.entity.CandidateAnswer;

import java.util.List;

public interface CandidateAnswerService {
    void saveCandidateAnswers(CandidateAnswer answers);
    boolean isCandidateAnswersExist(int idCandidate);
    List<CandidateAnswer> getAllAnswers();
    CandidateAnswer findById(int id);
    void updateCandidateAnswer(CandidateAnswer answers);
}
