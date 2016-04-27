package my.entity;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CandidateAnswer {
    private int id;
    private Map<Integer, String> answers;

    public CandidateAnswer() {
    }

    public CandidateAnswer(int id, Map<Integer, String> map) {
        this.id = id;
        this.answers = map;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Integer, String> answers) {
        this.answers = answers;
    }
}
