/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gamer
 */
@Entity
@Table(name = "QUIZRECORD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quizrecord.findAll", query = "SELECT q FROM Quizrecord q")
    , @NamedQuery(name = "Quizrecord.findByQuizrecordid", query = "SELECT q FROM Quizrecord q WHERE q.quizrecordid = :quizrecordid")
    , @NamedQuery(name = "Quizrecord.findByTotalscore", query = "SELECT q FROM Quizrecord q WHERE q.totalscore = :totalscore")})
public class Quizrecord implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUIZRECORDID")
    private Integer quizrecordid;
    @Column(name = "TOTALSCORE")
    private Integer totalscore;
    @JoinColumn(name = "QUIZES_QUIZID", referencedColumnName = "QUIZID")
    @OneToOne(optional = false)
    private Quizes quizesQuizid;
    @JoinColumn(name = "USERS_USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private Users usersUserid;

    public Quizrecord() {
    }

    public Quizrecord(Integer quizrecordid) {
        this.quizrecordid = quizrecordid;
    }

    public Integer getQuizrecordid() {
        return quizrecordid;
    }

    public void setQuizrecordid(Integer quizrecordid) {
        this.quizrecordid = quizrecordid;
    }

    public Integer getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(Integer totalscore) {
        this.totalscore = totalscore;
    }

    public Quizes getQuizesQuizid() {
        return quizesQuizid;
    }

    public void setQuizesQuizid(Quizes quizesQuizid) {
        this.quizesQuizid = quizesQuizid;
    }

    public Users getUsersUserid() {
        return usersUserid;
    }

    public void setUsersUserid(Users usersUserid) {
        this.usersUserid = usersUserid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (quizrecordid != null ? quizrecordid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quizrecord)) {
            return false;
        }
        Quizrecord other = (Quizrecord) object;
        if ((this.quizrecordid == null && other.quizrecordid != null) || (this.quizrecordid != null && !this.quizrecordid.equals(other.quizrecordid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Quizrecord[ quizrecordid=" + quizrecordid + " ]";
    }
    
}