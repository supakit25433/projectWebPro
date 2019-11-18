/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaClasses;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gamer
 */
@Entity
@Table(name = "CHOICES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Choices.findAll", query = "SELECT c FROM Choices c")
    , @NamedQuery(name = "Choices.findByChoiceid", query = "SELECT c FROM Choices c WHERE c.choiceid = :choiceid")
    , @NamedQuery(name = "Choices.findByChoice", query = "SELECT c FROM Choices c WHERE c.choice = :choice")
    , @NamedQuery(name = "Choices.findByIsCorrect", query = "SELECT c FROM Choices c WHERE c.isCorrect = :isCorrect")})
public class Choices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CHOICEID")
    private Integer choiceid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CHOICE")
    private String choice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_CORRECT")
    private Character isCorrect;
    @JoinColumn(name = "QUESTIONS_QUESTIONID", referencedColumnName = "QUESTIONID")
    @ManyToOne(optional = false)
    private Questions questionsQuestionid;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "choicesChoiceid")
    private StudentsChoice studentsChoice;

    public Choices() {
    }

    public Choices(Integer choiceid) {
        this.choiceid = choiceid;
    }

    public Choices(Integer choiceid, String choice, Character isCorrect) {
        this.choiceid = choiceid;
        this.choice = choice;
        this.isCorrect = isCorrect;
    }

    public Integer getChoiceid() {
        return choiceid;
    }

    public void setChoiceid(Integer choiceid) {
        this.choiceid = choiceid;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public Character getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Character isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Questions getQuestionsQuestionid() {
        return questionsQuestionid;
    }

    public void setQuestionsQuestionid(Questions questionsQuestionid) {
        this.questionsQuestionid = questionsQuestionid;
    }

    public StudentsChoice getStudentsChoice() {
        return studentsChoice;
    }

    public void setStudentsChoice(StudentsChoice studentsChoice) {
        this.studentsChoice = studentsChoice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (choiceid != null ? choiceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Choices)) {
            return false;
        }
        Choices other = (Choices) object;
        if ((this.choiceid == null && other.choiceid != null) || (this.choiceid != null && !this.choiceid.equals(other.choiceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaClasses.Choices[ choiceid=" + choiceid + " ]";
    }
    
}
