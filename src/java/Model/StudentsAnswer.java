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
public class StudentsAnswer {
    
    private int studentAnswer;
    private String answer;
    private int answerId;
    private int userId;

    public int getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(int studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public StudentsAnswer() {
    }
    
}
