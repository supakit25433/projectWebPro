/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaClasses;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gamer
 */
@Entity
@Table(name = "QUESTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Questions.findAll", query = "SELECT q FROM Questions q")
    , @NamedQuery(name = "Questions.findByQuestionid", query = "SELECT q FROM Questions q WHERE q.questionid = :questionid")
    , @NamedQuery(name = "Questions.findByQuestion", query = "SELECT q FROM Questions q WHERE q.question = :question")
    , @NamedQuery(name = "Questions.findByTypename", query = "SELECT q FROM Questions q WHERE q.typename = :typename")
    , @NamedQuery(name = "Questions.findByDescription", query = "SELECT q FROM Questions q WHERE q.description = :description")})
public class Questions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUESTIONID")
    private Integer questionid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "QUESTION")
    private String question;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "TYPENAME")
    private String typename;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "QUIZES_QUIZID", referencedColumnName = "QUIZID")
    @ManyToOne(optional = false)
    private Quizes quizesQuizid;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "questionsQuestionid")
    private Longanswer longanswer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionsQuestionid")
    private List<Choices> choicesList;

    public Questions() {
    }

    public Questions(Integer questionid) {
        this.questionid = questionid;
    }

    public Questions(Integer questionid, String question, String typename) {
        this.questionid = questionid;
        this.question = question;
        this.typename = typename;
    }

    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Quizes getQuizesQuizid() {
        return quizesQuizid;
    }

    public void setQuizesQuizid(Quizes quizesQuizid) {
        this.quizesQuizid = quizesQuizid;
    }

    public Longanswer getLonganswer() {
        return longanswer;
    }

    public void setLonganswer(Longanswer longanswer) {
        this.longanswer = longanswer;
    }

    @XmlTransient
    public List<Choices> getChoicesList() {
        return choicesList;
    }

    public void setChoicesList(List<Choices> choicesList) {
        this.choicesList = choicesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionid != null ? questionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Questions)) {
            return false;
        }
        Questions other = (Questions) object;
        if ((this.questionid == null && other.questionid != null) || (this.questionid != null && !this.questionid.equals(other.questionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Questions[ questionid=" + questionid + " ]";
    }
    
}
