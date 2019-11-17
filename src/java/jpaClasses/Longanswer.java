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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author surface
 */
@Entity
@Table(name = "LONGANSWER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Longanswer.findAll", query = "SELECT l FROM Longanswer l")
    , @NamedQuery(name = "Longanswer.findByAnswerid", query = "SELECT l FROM Longanswer l WHERE l.answerid = :answerid")
    , @NamedQuery(name = "Longanswer.findByAnswer", query = "SELECT l FROM Longanswer l WHERE l.answer = :answer")})
public class Longanswer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANSWERID")
    private Integer answerid;
    @Size(max = 500)
    @Column(name = "ANSWER")
    private String answer;
    @JoinColumn(name = "QUESTIONS_QUESTIONID", referencedColumnName = "QUESTIONID")
    @OneToOne(optional = false)
    private Questions questionsQuestionid;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "longanswerAnswerid")
    private StudentsAnswer studentsAnswer;

    public Longanswer() {
    }

    public Longanswer(Integer answerid) {
        this.answerid = answerid;
    }

    public Integer getAnswerid() {
        return answerid;
    }

    public void setAnswerid(Integer answerid) {
        this.answerid = answerid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Questions getQuestionsQuestionid() {
        return questionsQuestionid;
    }

    public void setQuestionsQuestionid(Questions questionsQuestionid) {
        this.questionsQuestionid = questionsQuestionid;
    }

    public StudentsAnswer getStudentsAnswer() {
        return studentsAnswer;
    }

    public void setStudentsAnswer(StudentsAnswer studentsAnswer) {
        this.studentsAnswer = studentsAnswer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (answerid != null ? answerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Longanswer)) {
            return false;
        }
        Longanswer other = (Longanswer) object;
        if ((this.answerid == null && other.answerid != null) || (this.answerid != null && !this.answerid.equals(other.answerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaClasses.Longanswer[ answerid=" + answerid + " ]";
    }
    
}
