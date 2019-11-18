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
import jpaClasses.Quizes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.RollbackFailureException;
import jpaClasses.Subjects;
import jpaClasses.UsersSubscription;

/**
 *
 * @author Gamer
 */
public class SubjectsJpaController implements Serializable {

    public SubjectsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Subjects subjects) throws RollbackFailureException, Exception {
        if (subjects.getQuizesList() == null) {
            subjects.setQuizesList(new ArrayList<Quizes>());
        }
        if (subjects.getUsersSubscriptionList() == null) {
            subjects.setUsersSubscriptionList(new ArrayList<UsersSubscription>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Quizes> attachedQuizesList = new ArrayList<Quizes>();
            for (Quizes quizesListQuizesToAttach : subjects.getQuizesList()) {
                quizesListQuizesToAttach = em.getReference(quizesListQuizesToAttach.getClass(), quizesListQuizesToAttach.getQuizid());
                attachedQuizesList.add(quizesListQuizesToAttach);
            }
            subjects.setQuizesList(attachedQuizesList);
            List<UsersSubscription> attachedUsersSubscriptionList = new ArrayList<UsersSubscription>();
            for (UsersSubscription usersSubscriptionListUsersSubscriptionToAttach : subjects.getUsersSubscriptionList()) {
                usersSubscriptionListUsersSubscriptionToAttach = em.getReference(usersSubscriptionListUsersSubscriptionToAttach.getClass(), usersSubscriptionListUsersSubscriptionToAttach.getSubscriptionid());
                attachedUsersSubscriptionList.add(usersSubscriptionListUsersSubscriptionToAttach);
            }
            subjects.setUsersSubscriptionList(attachedUsersSubscriptionList);
            em.persist(subjects);
            for (Quizes quizesListQuizes : subjects.getQuizesList()) {
                Subjects oldSubjectsSubjectidOfQuizesListQuizes = quizesListQuizes.getSubjectsSubjectid();
                quizesListQuizes.setSubjectsSubjectid(subjects);
                quizesListQuizes = em.merge(quizesListQuizes);
                if (oldSubjectsSubjectidOfQuizesListQuizes != null) {
                    oldSubjectsSubjectidOfQuizesListQuizes.getQuizesList().remove(quizesListQuizes);
                    oldSubjectsSubjectidOfQuizesListQuizes = em.merge(oldSubjectsSubjectidOfQuizesListQuizes);
                }
            }
            for (UsersSubscription usersSubscriptionListUsersSubscription : subjects.getUsersSubscriptionList()) {
                Subjects oldSubjectsSubjectidOfUsersSubscriptionListUsersSubscription = usersSubscriptionListUsersSubscription.getSubjectsSubjectid();
                usersSubscriptionListUsersSubscription.setSubjectsSubjectid(subjects);
                usersSubscriptionListUsersSubscription = em.merge(usersSubscriptionListUsersSubscription);
                if (oldSubjectsSubjectidOfUsersSubscriptionListUsersSubscription != null) {
                    oldSubjectsSubjectidOfUsersSubscriptionListUsersSubscription.getUsersSubscriptionList().remove(usersSubscriptionListUsersSubscription);
                    oldSubjectsSubjectidOfUsersSubscriptionListUsersSubscription = em.merge(oldSubjectsSubjectidOfUsersSubscriptionListUsersSubscription);
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

    public void edit(Subjects subjects) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Subjects persistentSubjects = em.find(Subjects.class, subjects.getSubjectid());
            List<Quizes> quizesListOld = persistentSubjects.getQuizesList();
            List<Quizes> quizesListNew = subjects.getQuizesList();
            List<UsersSubscription> usersSubscriptionListOld = persistentSubjects.getUsersSubscriptionList();
            List<UsersSubscription> usersSubscriptionListNew = subjects.getUsersSubscriptionList();
            List<String> illegalOrphanMessages = null;
            for (Quizes quizesListOldQuizes : quizesListOld) {
                if (!quizesListNew.contains(quizesListOldQuizes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Quizes " + quizesListOldQuizes + " since its subjectsSubjectid field is not nullable.");
                }
            }
            for (UsersSubscription usersSubscriptionListOldUsersSubscription : usersSubscriptionListOld) {
                if (!usersSubscriptionListNew.contains(usersSubscriptionListOldUsersSubscription)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UsersSubscription " + usersSubscriptionListOldUsersSubscription + " since its subjectsSubjectid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Quizes> attachedQuizesListNew = new ArrayList<Quizes>();
            for (Quizes quizesListNewQuizesToAttach : quizesListNew) {
                quizesListNewQuizesToAttach = em.getReference(quizesListNewQuizesToAttach.getClass(), quizesListNewQuizesToAttach.getQuizid());
                attachedQuizesListNew.add(quizesListNewQuizesToAttach);
            }
            quizesListNew = attachedQuizesListNew;
            subjects.setQuizesList(quizesListNew);
            List<UsersSubscription> attachedUsersSubscriptionListNew = new ArrayList<UsersSubscription>();
            for (UsersSubscription usersSubscriptionListNewUsersSubscriptionToAttach : usersSubscriptionListNew) {
                usersSubscriptionListNewUsersSubscriptionToAttach = em.getReference(usersSubscriptionListNewUsersSubscriptionToAttach.getClass(), usersSubscriptionListNewUsersSubscriptionToAttach.getSubscriptionid());
                attachedUsersSubscriptionListNew.add(usersSubscriptionListNewUsersSubscriptionToAttach);
            }
            usersSubscriptionListNew = attachedUsersSubscriptionListNew;
            subjects.setUsersSubscriptionList(usersSubscriptionListNew);
            subjects = em.merge(subjects);
            for (Quizes quizesListNewQuizes : quizesListNew) {
                if (!quizesListOld.contains(quizesListNewQuizes)) {
                    Subjects oldSubjectsSubjectidOfQuizesListNewQuizes = quizesListNewQuizes.getSubjectsSubjectid();
                    quizesListNewQuizes.setSubjectsSubjectid(subjects);
                    quizesListNewQuizes = em.merge(quizesListNewQuizes);
                    if (oldSubjectsSubjectidOfQuizesListNewQuizes != null && !oldSubjectsSubjectidOfQuizesListNewQuizes.equals(subjects)) {
                        oldSubjectsSubjectidOfQuizesListNewQuizes.getQuizesList().remove(quizesListNewQuizes);
                        oldSubjectsSubjectidOfQuizesListNewQuizes = em.merge(oldSubjectsSubjectidOfQuizesListNewQuizes);
                    }
                }
            }
            for (UsersSubscription usersSubscriptionListNewUsersSubscription : usersSubscriptionListNew) {
                if (!usersSubscriptionListOld.contains(usersSubscriptionListNewUsersSubscription)) {
                    Subjects oldSubjectsSubjectidOfUsersSubscriptionListNewUsersSubscription = usersSubscriptionListNewUsersSubscription.getSubjectsSubjectid();
                    usersSubscriptionListNewUsersSubscription.setSubjectsSubjectid(subjects);
                    usersSubscriptionListNewUsersSubscription = em.merge(usersSubscriptionListNewUsersSubscription);
                    if (oldSubjectsSubjectidOfUsersSubscriptionListNewUsersSubscription != null && !oldSubjectsSubjectidOfUsersSubscriptionListNewUsersSubscription.equals(subjects)) {
                        oldSubjectsSubjectidOfUsersSubscriptionListNewUsersSubscription.getUsersSubscriptionList().remove(usersSubscriptionListNewUsersSubscription);
                        oldSubjectsSubjectidOfUsersSubscriptionListNewUsersSubscription = em.merge(oldSubjectsSubjectidOfUsersSubscriptionListNewUsersSubscription);
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
                Integer id = subjects.getSubjectid();
                if (findSubjects(id) == null) {
                    throw new NonexistentEntityException("The subjects with id " + id + " no longer exists.");
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
            Subjects subjects;
            try {
                subjects = em.getReference(Subjects.class, id);
                subjects.getSubjectid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The subjects with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Quizes> quizesListOrphanCheck = subjects.getQuizesList();
            for (Quizes quizesListOrphanCheckQuizes : quizesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Subjects (" + subjects + ") cannot be destroyed since the Quizes " + quizesListOrphanCheckQuizes + " in its quizesList field has a non-nullable subjectsSubjectid field.");
            }
            List<UsersSubscription> usersSubscriptionListOrphanCheck = subjects.getUsersSubscriptionList();
            for (UsersSubscription usersSubscriptionListOrphanCheckUsersSubscription : usersSubscriptionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Subjects (" + subjects + ") cannot be destroyed since the UsersSubscription " + usersSubscriptionListOrphanCheckUsersSubscription + " in its usersSubscriptionList field has a non-nullable subjectsSubjectid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(subjects);
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

    public List<Subjects> findSubjectsEntities() {
        return findSubjectsEntities(true, -1, -1);
    }

    public List<Subjects> findSubjectsEntities(int maxResults, int firstResult) {
        return findSubjectsEntities(false, maxResults, firstResult);
    }

    private List<Subjects> findSubjectsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Subjects.class));
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

    public Subjects findSubjects(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Subjects.class, id);
        } finally {
            em.close();
        }
    }

    public int getSubjectsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Subjects> rt = cq.from(Subjects.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
