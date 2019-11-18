/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaClasses;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gamer
 */
@Entity
@Table(name = "STUDENTS_CHOICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentsChoice.findAll", query = "SELECT s FROM StudentsChoice s")
    , @NamedQuery(name = "StudentsChoice.findByStudentchoiceid", query = "SELECT s FROM StudentsChoice s WHERE s.studentchoiceid = :studentchoiceid")})
public class StudentsChoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "STUDENTCHOICEID")
    private Integer studentchoiceid;
    @JoinColumn(name = "CHOICES_CHOICEID", referencedColumnName = "CHOICEID")
    @OneToOne(optional = false)
    private Choices choicesChoiceid;
    @JoinColumn(name = "USERS_USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private Users usersUserid;

    public StudentsChoice() {
    }

    public StudentsChoice(Integer studentchoiceid) {
        this.studentchoiceid = studentchoiceid;
    }

    public Integer getStudentchoiceid() {
        return studentchoiceid;
    }

    public void setStudentchoiceid(Integer studentchoiceid) {
        this.studentchoiceid = studentchoiceid;
    }

    public Choices getChoicesChoiceid() {
        return choicesChoiceid;
    }

    public void setChoicesChoiceid(Choices choicesChoiceid) {
        this.choicesChoiceid = choicesChoiceid;
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
        hash += (studentchoiceid != null ? studentchoiceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentsChoice)) {
            return false;
        }
        StudentsChoice other = (StudentsChoice) object;
        if ((this.studentchoiceid == null && other.studentchoiceid != null) || (this.studentchoiceid != null && !this.studentchoiceid.equals(other.studentchoiceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaClasses.StudentsChoice[ studentchoiceid=" + studentchoiceid + " ]";
    }
    
}
