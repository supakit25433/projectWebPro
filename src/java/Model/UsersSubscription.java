/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author nar-u
 */
public class UsersSubscription {
    
    private int subscriptionid;
    private int userid;
    private int subjectid;

    public int getSubscriptionid() {
        return subscriptionid;
    }

    public void setSubscriptionid(int subscriptionid) {
        this.subscriptionid = subscriptionid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(int subjectid) {
        this.subjectid = subjectid;
    }

    public UsersSubscription() {
    }

    public UsersSubscription(int subscriptionid) {
        this.subscriptionid = subscriptionid;
    }
    
}
