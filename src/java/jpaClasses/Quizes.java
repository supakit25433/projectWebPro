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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "QUIZES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quizes.findAll", query = "SELECT q FROM Quizes q")
    , @NamedQuery(name = "Quizes.findByQuizid", query = "SELECT q FROM Quizes q WHERE q.quizid = :quizid")
    , @NamedQuery(name = "Quizes.findByQuizname", query = "SELECT q FROM Quizes q WHERE q.quizname = :quizname")
    , @NamedQuery(name = "Quizes.findByDescription", query = "SELECT q FROM Quizes q WHERE q.description = :description")})
public class Quizes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "QUIZID")
    private Integer quizid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "QUIZNAME")
    private String quizname;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quizesQuizid")
    private List<Questions> questionsList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "quizesQuizid")
    private Quizrecord quizrecord;
    @JoinColumn(name = "SUBJECTS_SUBJECTID", referencedColumnName = "SUBJECTID")
    @ManyToOne(optional = false)
    private Subjects subjectsSubjectid;

    public Quizes() {
    }

    public Quizes(Integer quizid) {
        this.quizid = quizid;
    }

    public Quizes(Integer quizid, String quizname) {
        this.quizid = quizid;
        this.quizname = quizname;
    }

    public Integer getQuizid() {
        return quizid;
    }

    public void setQuizid(Integer quizid) {
        this.quizid = quizid;
    }

    public String getQuizname() {
        return quizname;
    }

    public void setQuizname(String quizname) {
        this.quizname = quizname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<Questions> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<Questions> questionsList) {
        this.questionsList = questionsList;
    }

    public Quizrecord getQuizrecord() {
        return quizrecord;
    }

    public void setQuizrecord(Quizrecord quizrecord) {
        this.quizrecord = quizrecord;
    }

    public Subjects getSubjectsSubjectid() {
        return subjectsSubjectid;
    }

    public void setSubjectsSubjectid(Subjects subjectsSubjectid) {
        this.subjectsSubjectid = subjectsSubjectid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (quizid != null ? quizid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quizes)) {
            return false;
        }
        Quizes other = (Quizes) object;
        if ((this.quizid == null && other.quizid != null) || (this.quizid != null && !this.quizid.equals(other.quizid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaClasses.Quizes[ quizid=" + quizid + " ]";
    }
    
}
