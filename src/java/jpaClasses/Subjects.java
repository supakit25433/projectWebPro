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
@Table(name = "SUBJECTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subjects.findAll", query = "SELECT s FROM Subjects s")
    , @NamedQuery(name = "Subjects.findBySubjectid", query = "SELECT s FROM Subjects s WHERE s.subjectid = :subjectid")
    , @NamedQuery(name = "Subjects.findBySubjectname", query = "SELECT s FROM Subjects s WHERE s.subjectname = :subjectname")
    , @NamedQuery(name = "Subjects.findByDescription", query = "SELECT s FROM Subjects s WHERE s.description = :description")})
public class Subjects implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SUBJECTID")
    private Integer subjectid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "SUBJECTNAME")
    private String subjectname;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "USERS_USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private Users usersUserid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectsSubjectid")
    private List<Quizes> quizesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectsSubjectid")
    private List<UsersSubscription> usersSubscriptionList;

    public Subjects() {
    }

    public Subjects(Integer subjectid) {
        this.subjectid = subjectid;
    }

    public Subjects(Integer subjectid, String subjectname) {
        this.subjectid = subjectid;
        this.subjectname = subjectname;
    }

    public Subjects(String subjectname, String description, Users usersUserid) {
        this.subjectname = subjectname;
        this.description = description;
        this.usersUserid = usersUserid;
    }

    public Integer getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getUsersUserid() {
        return usersUserid;
    }

    public void setUsersUserid(Users usersUserid) {
        this.usersUserid = usersUserid;
    }

    @XmlTransient
    public List<Quizes> getQuizesList() {
        return quizesList;
    }

    public void setQuizesList(List<Quizes> quizesList) {
        this.quizesList = quizesList;
    }

    @XmlTransient
    public List<UsersSubscription> getUsersSubscriptionList() {
        return usersSubscriptionList;
    }

    public void setUsersSubscriptionList(List<UsersSubscription> usersSubscriptionList) {
        this.usersSubscriptionList = usersSubscriptionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjectid != null ? subjectid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subjects)) {
            return false;
        }
        Subjects other = (Subjects) object;
        if ((this.subjectid == null && other.subjectid != null) || (this.subjectid != null && !this.subjectid.equals(other.subjectid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaClasses.Subjects[ subjectid=" + subjectid + " ]";
    }
    
}
