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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gamer
 */
@Entity
@Table(name = "USERS_SUBSCRIPTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersSubscription.findAll", query = "SELECT u FROM UsersSubscription u")
    , @NamedQuery(name = "UsersSubscription.findBySubscriptionid", query = "SELECT u FROM UsersSubscription u WHERE u.subscriptionid = :subscriptionid")})
public class UsersSubscription implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SUBSCRIPTIONID")
    private Integer subscriptionid;
    @JoinColumn(name = "SUBJECTS_SUBJECTID", referencedColumnName = "SUBJECTID")
    @ManyToOne(optional = false)
    private Subjects subjectsSubjectid;
    @JoinColumn(name = "USERS_USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private Users usersUserid;

    public UsersSubscription() {
    }

    public UsersSubscription(Integer subscriptionid) {
        this.subscriptionid = subscriptionid;
    }

    public UsersSubscription(Subjects subjectsSubjectid, Users usersUserid) {
        this.subjectsSubjectid = subjectsSubjectid;
        this.usersUserid = usersUserid;
    }

    public Integer getSubscriptionid() {
        return subscriptionid;
    }

    public void setSubscriptionid(Integer subscriptionid) {
        this.subscriptionid = subscriptionid;
    }

    public Subjects getSubjectsSubjectid() {
        return subjectsSubjectid;
    }

    public void setSubjectsSubjectid(Subjects subjectsSubjectid) {
        this.subjectsSubjectid = subjectsSubjectid;
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
        hash += (subscriptionid != null ? subscriptionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersSubscription)) {
            return false;
        }
        UsersSubscription other = (UsersSubscription) object;
        if ((this.subscriptionid == null && other.subscriptionid != null) || (this.subscriptionid != null && !this.subscriptionid.equals(other.subscriptionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaClasses.UsersSubscription[ subscriptionid=" + subscriptionid + " ]";
    }
    
}
