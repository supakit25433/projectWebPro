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
public class QuizRecord {
    
    private int quizRecordId;
    private int totalScore;
    private int quizId;
    private int userId;

    public int getQuizRecordId() {
        return quizRecordId;
    }

    public void setQuizRecordId(int quizRecordId) {
        this.quizRecordId = quizRecordId;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public QuizRecord() {
    }

    public QuizRecord(int quizRecordId) {
        this.quizRecordId = quizRecordId;
    }

    public QuizRecord(int quizRecordId, int quizId, int userId) {
        this.quizRecordId = quizRecordId;
        this.quizId = quizId;
        this.userId = userId;
    }

    public QuizRecord(int quizRecordId, int quizId) {
        this.quizRecordId = quizRecordId;
        this.quizId = quizId;
    }
    
    
    
}
