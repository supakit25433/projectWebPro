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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gamer
 */
@Entity
@Table(name = "STUDENTS_ANSWER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentsAnswer.findAll", query = "SELECT s FROM StudentsAnswer s")
    , @NamedQuery(name = "StudentsAnswer.findByStudentanswerid", query = "SELECT s FROM StudentsAnswer s WHERE s.studentanswerid = :studentanswerid")
    , @NamedQuery(name = "StudentsAnswer.findByAnswer", query = "SELECT s FROM StudentsAnswer s WHERE s.answer = :answer")})
public class StudentsAnswer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "STUDENTANSWERID")
    private Integer studentanswerid;
    @Size(max = 255)
    @Column(name = "ANSWER")
    private String answer;
    @JoinColumn(name = "LONGANSWER_ANSWERID", referencedColumnName = "ANSWERID")
    @OneToOne(optional = false)
    private Longanswer longanswerAnswerid;
    @JoinColumn(name = "USERS_USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private Users usersUserid;

    public StudentsAnswer() {
    }

    public StudentsAnswer(Integer studentanswerid) {
        this.studentanswerid = studentanswerid;
    }

    public Integer getStudentanswerid() {
        return studentanswerid;
    }

    public void setStudentanswerid(Integer studentanswerid) {
        this.studentanswerid = studentanswerid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Longanswer getLonganswerAnswerid() {
        return longanswerAnswerid;
    }

    public void setLonganswerAnswerid(Longanswer longanswerAnswerid) {
        this.longanswerAnswerid = longanswerAnswerid;
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
        hash += (studentanswerid != null ? studentanswerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentsAnswer)) {
            return false;
        }
        StudentsAnswer other = (StudentsAnswer) object;
        if ((this.studentanswerid == null && other.studentanswerid != null) || (this.studentanswerid != null && !this.studentanswerid.equals(other.studentanswerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.StudentsAnswer[ studentanswerid=" + studentanswerid + " ]";
    }
    
}
