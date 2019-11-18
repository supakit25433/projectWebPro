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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByUserid", query = "SELECT u FROM Users u WHERE u.userid = :userid")
    , @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username")
    , @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")
    , @NamedQuery(name = "Users.findByFullname", query = "SELECT u FROM Users u WHERE u.fullname = :fullname")
    , @NamedQuery(name = "Users.findByTypename", query = "SELECT u FROM Users u WHERE u.typename = :typename")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USERID")
    private Integer userid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "FULLNAME")
    private String fullname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "TYPENAME")
    private String typename;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersUserid")
    private List<Subjects> subjectsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersUserid")
    private List<Quizrecord> quizrecordList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersUserid")
    private List<StudentsAnswer> studentsAnswerList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersUserid")
    private List<UsersSubscription> usersSubscriptionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersUserid")
    private List<StudentsChoice> studentsChoiceList;

    public Users() {
    }

    public Users(Integer userid) {
        this.userid = userid;
    }

    public Users(Integer userid, String username, String password, String fullname, String typename) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.typename = typename;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @XmlTransient
    public List<Subjects> getSubjectsList() {
        return subjectsList;
    }

    public void setSubjectsList(List<Subjects> subjectsList) {
        this.subjectsList = subjectsList;
    }

    @XmlTransient
    public List<Quizrecord> getQuizrecordList() {
        return quizrecordList;
    }

    public void setQuizrecordList(List<Quizrecord> quizrecordList) {
        this.quizrecordList = quizrecordList;
    }

    @XmlTransient
    public List<StudentsAnswer> getStudentsAnswerList() {
        return studentsAnswerList;
    }

    public void setStudentsAnswerList(List<StudentsAnswer> studentsAnswerList) {
        this.studentsAnswerList = studentsAnswerList;
    }

    @XmlTransient
    public List<UsersSubscription> getUsersSubscriptionList() {
        return usersSubscriptionList;
    }

    public void setUsersSubscriptionList(List<UsersSubscription> usersSubscriptionList) {
        this.usersSubscriptionList = usersSubscriptionList;
    }

    @XmlTransient
    public List<StudentsChoice> getStudentsChoiceList() {
        return studentsChoiceList;
    }

    public void setStudentsChoiceList(List<StudentsChoice> studentsChoiceList) {
        this.studentsChoiceList = studentsChoiceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaClasses.Users[ userid=" + userid + " ]";
    }
    
}
