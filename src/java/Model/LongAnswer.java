/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author nar-u
 */
public class LongAnswer {
    
    private int answerId;
    private String answer;
    private int questionId;

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public LongAnswer() {
    }

    public LongAnswer(int answerId) {
        this.answerId = answerId;
    }

    public LongAnswer(int answerId, int questionId) {
        this.answerId = answerId;
        this.questionId = questionId;
    }
    
}
