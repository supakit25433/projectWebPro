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
public class Choices {
    
    private int choiceId;
    private String choice;
    private boolean isCorrect;
    private int questionId;

    public int getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(int choiceId) {
        this.choiceId = choiceId;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public Choices() {
    }

    public Choices(int choiceId) {
        this.choiceId = choiceId;
    }

    public Choices(int choiceId, int questionId) {
        this.choiceId = choiceId;
        this.questionId = questionId;
    }
    
}
