/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpaClasses.Subjects;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.RollbackFailureException;
import jpaClasses.Quizrecord;
import jpaClasses.Longanswer;
import jpaClasses.UsersSubscription;
import jpaClasses.StudentsChoice;
import jpaClasses.Users;

/**
 *
 * @author Gamer
 */
public class UsersJpaController implements Serializable {

    public UsersJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Users users) throws RollbackFailureException, Exception {
        if (users.getSubjectsList() == null) {
            users.setSubjectsList(new ArrayList<Subjects>());
        }
        if (users.getQuizrecordList() == null) {
            users.setQuizrecordList(new ArrayList<Quizrecord>());
        }
        if (users.getLonganswerList() == null) {
            users.setLonganswerList(new ArrayList<Longanswer>());
        }
        if (users.getUsersSubscriptionList() == null) {
            users.setUsersSubscriptionList(new ArrayList<UsersSubscription>());
        }
        if (users.getStudentsChoiceList() == null) {
            users.setStudentsChoiceList(new ArrayList<StudentsChoice>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Subjects> attachedSubjectsList = new ArrayList<Subjects>();
            for (Subjects subjectsListSubjectsToAttach : users.getSubjectsList()) {
                subjectsListSubjectsToAttach = em.getReference(subjectsListSubjectsToAttach.getClass(), subjectsListSubjectsToAttach.getSubjectid());
                attachedSubjectsList.add(subjectsListSubjectsToAttach);
            }
            users.setSubjectsList(attachedSubjectsList);
            List<Quizrecord> attachedQuizrecordList = new ArrayList<Quizrecord>();
            for (Quizrecord quizrecordListQuizrecordToAttach : users.getQuizrecordList()) {
                quizrecordListQuizrecordToAttach = em.getReference(quizrecordListQuizrecordToAttach.getClass(), quizrecordListQuizrecordToAttach.getQuizrecordid());
                attachedQuizrecordList.add(quizrecordListQuizrecordToAttach);
            }
            users.setQuizrecordList(attachedQuizrecordList);
            List<Longanswer> attachedLonganswerList = new ArrayList<Longanswer>();
            for (Longanswer longanswerListLonganswerToAttach : users.getLonganswerList()) {
                longanswerListLonganswerToAttach = em.getReference(longanswerListLonganswerToAttach.getClass(), longanswerListLonganswerToAttach.getAnswerid());
                attachedLonganswerList.add(longanswerListLonganswerToAttach);
            }
            users.setLonganswerList(attachedLonganswerList);
            List<UsersSubscription> attachedUsersSubscriptionList = new ArrayList<UsersSubscription>();
            for (UsersSubscription usersSubscriptionListUsersSubscriptionToAttach : users.getUsersSubscriptionList()) {
                usersSubscriptionListUsersSubscriptionToAttach = em.getReference(usersSubscriptionListUsersSubscriptionToAttach.getClass(), usersSubscriptionListUsersSubscriptionToAttach.getSubscriptionid());
                attachedUsersSubscriptionList.add(usersSubscriptionListUsersSubscriptionToAttach);
            }
            users.setUsersSubscriptionList(attachedUsersSubscriptionList);
            List<StudentsChoice> attachedStudentsChoiceList = new ArrayList<StudentsChoice>();
            for (StudentsChoice studentsChoiceListStudentsChoiceToAttach : users.getStudentsChoiceList()) {
                studentsChoiceListStudentsChoiceToAttach = em.getReference(studentsChoiceListStudentsChoiceToAttach.getClass(), studentsChoiceListStudentsChoiceToAttach.getStudentchoiceid());
                attachedStudentsChoiceList.add(studentsChoiceListStudentsChoiceToAttach);
            }
            users.setStudentsChoiceList(attachedStudentsChoiceList);
            em.persist(users);
            for (Subjects subjectsListSubjects : users.getSubjectsList()) {
                Users oldUsersUseridOfSubjectsListSubjects = subjectsListSubjects.getUsersUserid();
                subjectsListSubjects.setUsersUserid(users);
                subjectsListSubjects = em.merge(subjectsListSubjects);
                if (oldUsersUseridOfSubjectsListSubjects != null) {
                    oldUsersUseridOfSubjectsListSubjects.getSubjectsList().remove(subjectsListSubjects);
                    oldUsersUseridOfSubjectsListSubjects = em.merge(oldUsersUseridOfSubjectsListSubjects);
                }
            }
            for (Quizrecord quizrecordListQuizrecord : users.getQuizrecordList()) {
                Users oldUsersUseridOfQuizrecordListQuizrecord = quizrecordListQuizrecord.getUsersUserid();
                quizrecordListQuizrecord.setUsersUserid(users);
                quizrecordListQuizrecord = em.merge(quizrecordListQuizrecord);
                if (oldUsersUseridOfQuizrecordListQuizrecord != null) {
                    oldUsersUseridOfQuizrecordListQuizrecord.getQuizrecordList().remove(quizrecordListQuizrecord);
                    oldUsersUseridOfQuizrecordListQuizrecord = em.merge(oldUsersUseridOfQuizrecordListQuizrecord);
                }
            }
            for (Longanswer longanswerListLonganswer : users.getLonganswerList()) {
                Users oldUsersUseridOfLonganswerListLonganswer = longanswerListLonganswer.getUsersUserid();
                longanswerListLonganswer.setUsersUserid(users);
                longanswerListLonganswer = em.merge(longanswerListLonganswer);
                if (oldUsersUseridOfLonganswerListLonganswer != null) {
                    oldUsersUseridOfLonganswerListLonganswer.getLonganswerList().remove(longanswerListLonganswer);
                    oldUsersUseridOfLonganswerListLonganswer = em.merge(oldUsersUseridOfLonganswerListLonganswer);
                }
            }
            for (UsersSubscription usersSubscriptionListUsersSubscription : users.getUsersSubscriptionList()) {
                Users oldUsersUseridOfUsersSubscriptionListUsersSubscription = usersSubscriptionListUsersSubscription.getUsersUserid();
                usersSubscriptionListUsersSubscription.setUsersUserid(users);
                usersSubscriptionListUsersSubscription = em.merge(usersSubscriptionListUsersSubscription);
                if (oldUsersUseridOfUsersSubscriptionListUsersSubscription != null) {
                    oldUsersUseridOfUsersSubscriptionListUsersSubscription.getUsersSubscriptionList().remove(usersSubscriptionListUsersSubscription);
                    oldUsersUseridOfUsersSubscriptionListUsersSubscription = em.merge(oldUsersUseridOfUsersSubscriptionListUsersSubscription);
                }
            }
            for (StudentsChoice studentsChoiceListStudentsChoice : users.getStudentsChoiceList()) {
                Users oldUsersUseridOfStudentsChoiceListStudentsChoice = studentsChoiceListStudentsChoice.getUsersUserid();
                studentsChoiceListStudentsChoice.setUsersUserid(users);
                studentsChoiceListStudentsChoice = em.merge(studentsChoiceListStudentsChoice);
                if (oldUsersUseridOfStudentsChoiceListStudentsChoice != null) {
                    oldUsersUseridOfStudentsChoiceListStudentsChoice.getStudentsChoiceList().remove(studentsChoiceListStudentsChoice);
                    oldUsersUseridOfStudentsChoiceListStudentsChoice = em.merge(oldUsersUseridOfStudentsChoiceListStudentsChoice);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Users persistentUsers = em.find(Users.class, users.getUserid());
            List<Subjects> subjectsListOld = persistentUsers.getSubjectsList();
            List<Subjects> subjectsListNew = users.getSubjectsList();
            List<Quizrecord> quizrecordListOld = persistentUsers.getQuizrecordList();
            List<Quizrecord> quizrecordListNew = users.getQuizrecordList();
            List<Longanswer> longanswerListOld = persistentUsers.getLonganswerList();
            List<Longanswer> longanswerListNew = users.getLonganswerList();
            List<UsersSubscription> usersSubscriptionListOld = persistentUsers.getUsersSubscriptionList();
            List<UsersSubscription> usersSubscriptionListNew = users.getUsersSubscriptionList();
            List<StudentsChoice> studentsChoiceListOld = persistentUsers.getStudentsChoiceList();
            List<StudentsChoice> studentsChoiceListNew = users.getStudentsChoiceList();
            List<String> illegalOrphanMessages = null;
            for (Subjects subjectsListOldSubjects : subjectsListOld) {
                if (!subjectsListNew.contains(subjectsListOldSubjects)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Subjects " + subjectsListOldSubjects + " since its usersUserid field is not nullable.");
                }
            }
            for (Quizrecord quizrecordListOldQuizrecord : quizrecordListOld) {
                if (!quizrecordListNew.contains(quizrecordListOldQuizrecord)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Quizrecord " + quizrecordListOldQuizrecord + " since its usersUserid field is not nullable.");
                }
            }
            for (Longanswer longanswerListOldLonganswer : longanswerListOld) {
                if (!longanswerListNew.contains(longanswerListOldLonganswer)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Longanswer " + longanswerListOldLonganswer + " since its usersUserid field is not nullable.");
                }
            }
            for (UsersSubscription usersSubscriptionListOldUsersSubscription : usersSubscriptionListOld) {
                if (!usersSubscriptionListNew.contains(usersSubscriptionListOldUsersSubscription)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UsersSubscription " + usersSubscriptionListOldUsersSubscription + " since its usersUserid field is not nullable.");
                }
            }
            for (StudentsChoice studentsChoiceListOldStudentsChoice : studentsChoiceListOld) {
                if (!studentsChoiceListNew.contains(studentsChoiceListOldStudentsChoice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain StudentsChoice " + studentsChoiceListOldStudentsChoice + " since its usersUserid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Subjects> attachedSubjectsListNew = new ArrayList<Subjects>();
            for (Subjects subjectsListNewSubjectsToAttach : subjectsListNew) {
                subjectsListNewSubjectsToAttach = em.getReference(subjectsListNewSubjectsToAttach.getClass(), subjectsListNewSubjectsToAttach.getSubjectid());
                attachedSubjectsListNew.add(subjectsListNewSubjectsToAttach);
            }
            subjectsListNew = attachedSubjectsListNew;
            users.setSubjectsList(subjectsListNew);
            List<Quizrecord> attachedQuizrecordListNew = new ArrayList<Quizrecord>();
            for (Quizrecord quizrecordListNewQuizrecordToAttach : quizrecordListNew) {
                quizrecordListNewQuizrecordToAttach = em.getReference(quizrecordListNewQuizrecordToAttach.getClass(), quizrecordListNewQuizrecordToAttach.getQuizrecordid());
                attachedQuizrecordListNew.add(quizrecordListNewQuizrecordToAttach);
            }
            quizrecordListNew = attachedQuizrecordListNew;
            users.setQuizrecordList(quizrecordListNew);
            List<Longanswer> attachedLonganswerListNew = new ArrayList<Longanswer>();
            for (Longanswer longanswerListNewLonganswerToAttach : longanswerListNew) {
                longanswerListNewLonganswerToAttach = em.getReference(longanswerListNewLonganswerToAttach.getClass(), longanswerListNewLonganswerToAttach.getAnswerid());
                attachedLonganswerListNew.add(longanswerListNewLonganswerToAttach);
            }
            longanswerListNew = attachedLonganswerListNew;
            users.setLonganswerList(longanswerListNew);
            List<UsersSubscription> attachedUsersSubscriptionListNew = new ArrayList<UsersSubscription>();
            for (UsersSubscription usersSubscriptionListNewUsersSubscriptionToAttach : usersSubscriptionListNew) {
                usersSubscriptionListNewUsersSubscriptionToAttach = em.getReference(usersSubscriptionListNewUsersSubscriptionToAttach.getClass(), usersSubscriptionListNewUsersSubscriptionToAttach.getSubscriptionid());
                attachedUsersSubscriptionListNew.add(usersSubscriptionListNewUsersSubscriptionToAttach);
            }
            usersSubscriptionListNew = attachedUsersSubscriptionListNew;
            users.setUsersSubscriptionList(usersSubscriptionListNew);
            List<StudentsChoice> attachedStudentsChoiceListNew = new ArrayList<StudentsChoice>();
            for (StudentsChoice studentsChoiceListNewStudentsChoiceToAttach : studentsChoiceListNew) {
                studentsChoiceListNewStudentsChoiceToAttach = em.getReference(studentsChoiceListNewStudentsChoiceToAttach.getClass(), studentsChoiceListNewStudentsChoiceToAttach.getStudentchoiceid());
                attachedStudentsChoiceListNew.add(studentsChoiceListNewStudentsChoiceToAttach);
            }
            studentsChoiceListNew = attachedStudentsChoiceListNew;
            users.setStudentsChoiceList(studentsChoiceListNew);
            users = em.merge(users);
            for (Subjects subjectsListNewSubjects : subjectsListNew) {
                if (!subjectsListOld.contains(subjectsListNewSubjects)) {
                    Users oldUsersUseridOfSubjectsListNewSubjects = subjectsListNewSubjects.getUsersUserid();
                    subjectsListNewSubjects.setUsersUserid(users);
                    subjectsListNewSubjects = em.merge(subjectsListNewSubjects);
                    if (oldUsersUseridOfSubjectsListNewSubjects != null && !oldUsersUseridOfSubjectsListNewSubjects.equals(users)) {
                        oldUsersUseridOfSubjectsListNewSubjects.getSubjectsList().remove(subjectsListNewSubjects);
                        oldUsersUseridOfSubjectsListNewSubjects = em.merge(oldUsersUseridOfSubjectsListNewSubjects);
                    }
                }
            }
            for (Quizrecord quizrecordListNewQuizrecord : quizrecordListNew) {
                if (!quizrecordListOld.contains(quizrecordListNewQuizrecord)) {
                    Users oldUsersUseridOfQuizrecordListNewQuizrecord = quizrecordListNewQuizrecord.getUsersUserid();
                    quizrecordListNewQuizrecord.setUsersUserid(users);
                    quizrecordListNewQuizrecord = em.merge(quizrecordListNewQuizrecord);
                    if (oldUsersUseridOfQuizrecordListNewQuizrecord != null && !oldUsersUseridOfQuizrecordListNewQuizrecord.equals(users)) {
                        oldUsersUseridOfQuizrecordListNewQuizrecord.getQuizrecordList().remove(quizrecordListNewQuizrecord);
                        oldUsersUseridOfQuizrecordListNewQuizrecord = em.merge(oldUsersUseridOfQuizrecordListNewQuizrecord);
                    }
                }
            }
            for (Longanswer longanswerListNewLonganswer : longanswerListNew) {
                if (!longanswerListOld.contains(longanswerListNewLonganswer)) {
                    Users oldUsersUseridOfLonganswerListNewLonganswer = longanswerListNewLonganswer.getUsersUserid();
                    longanswerListNewLonganswer.setUsersUserid(users);
                    longanswerListNewLonganswer = em.merge(longanswerListNewLonganswer);
                    if (oldUsersUseridOfLonganswerListNewLonganswer != null && !oldUsersUseridOfLonganswerListNewLonganswer.equals(users)) {
                        oldUsersUseridOfLonganswerListNewLonganswer.getLonganswerList().remove(longanswerListNewLonganswer);
                        oldUsersUseridOfLonganswerListNewLonganswer = em.merge(oldUsersUseridOfLonganswerListNewLonganswer);
                    }
                }
            }
            for (UsersSubscription usersSubscriptionListNewUsersSubscription : usersSubscriptionListNew) {
                if (!usersSubscriptionListOld.contains(usersSubscriptionListNewUsersSubscription)) {
                    Users oldUsersUseridOfUsersSubscriptionListNewUsersSubscription = usersSubscriptionListNewUsersSubscription.getUsersUserid();
                    usersSubscriptionListNewUsersSubscription.setUsersUserid(users);
                    usersSubscriptionListNewUsersSubscription = em.merge(usersSubscriptionListNewUsersSubscription);
                    if (oldUsersUseridOfUsersSubscriptionListNewUsersSubscription != null && !oldUsersUseridOfUsersSubscriptionListNewUsersSubscription.equals(users)) {
                        oldUsersUseridOfUsersSubscriptionListNewUsersSubscription.getUsersSubscriptionList().remove(usersSubscriptionListNewUsersSubscription);
                        oldUsersUseridOfUsersSubscriptionListNewUsersSubscription = em.merge(oldUsersUseridOfUsersSubscriptionListNewUsersSubscription);
                    }
                }
            }
            for (StudentsChoice studentsChoiceListNewStudentsChoice : studentsChoiceListNew) {
                if (!studentsChoiceListOld.contains(studentsChoiceListNewStudentsChoice)) {
                    Users oldUsersUseridOfStudentsChoiceListNewStudentsChoice = studentsChoiceListNewStudentsChoice.getUsersUserid();
                    studentsChoiceListNewStudentsChoice.setUsersUserid(users);
                    studentsChoiceListNewStudentsChoice = em.merge(studentsChoiceListNewStudentsChoice);
                    if (oldUsersUseridOfStudentsChoiceListNewStudentsChoice != null && !oldUsersUseridOfStudentsChoiceListNewStudentsChoice.equals(users)) {
                        oldUsersUseridOfStudentsChoiceListNewStudentsChoice.getStudentsChoiceList().remove(studentsChoiceListNewStudentsChoice);
                        oldUsersUseridOfStudentsChoiceListNewStudentsChoice = em.merge(oldUsersUseridOfStudentsChoiceListNewStudentsChoice);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = users.getUserid();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getUserid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Subjects> subjectsListOrphanCheck = users.getSubjectsList();
            for (Subjects subjectsListOrphanCheckSubjects : subjectsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the Subjects " + subjectsListOrphanCheckSubjects + " in its subjectsList field has a non-nullable usersUserid field.");
            }
            List<Quizrecord> quizrecordListOrphanCheck = users.getQuizrecordList();
            for (Quizrecord quizrecordListOrphanCheckQuizrecord : quizrecordListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the Quizrecord " + quizrecordListOrphanCheckQuizrecord + " in its quizrecordList field has a non-nullable usersUserid field.");
            }
            List<Longanswer> longanswerListOrphanCheck = users.getLonganswerList();
            for (Longanswer longanswerListOrphanCheckLonganswer : longanswerListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the Longanswer " + longanswerListOrphanCheckLonganswer + " in its longanswerList field has a non-nullable usersUserid field.");
            }
            List<UsersSubscription> usersSubscriptionListOrphanCheck = users.getUsersSubscriptionList();
            for (UsersSubscription usersSubscriptionListOrphanCheckUsersSubscription : usersSubscriptionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the UsersSubscription " + usersSubscriptionListOrphanCheckUsersSubscription + " in its usersSubscriptionList field has a non-nullable usersUserid field.");
            }
            List<StudentsChoice> studentsChoiceListOrphanCheck = users.getStudentsChoiceList();
            for (StudentsChoice studentsChoiceListOrphanCheckStudentsChoice : studentsChoiceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the StudentsChoice " + studentsChoiceListOrphanCheckStudentsChoice + " in its studentsChoiceList field has a non-nullable usersUserid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(users);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Users findUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
