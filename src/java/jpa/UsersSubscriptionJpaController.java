/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.RollbackFailureException;
import jpaClasses.Subjects;
import jpaClasses.UsersSubscription;

/**
 *
 * @author Gamer
 */
public class UsersSubscriptionJpaController implements Serializable {

    public UsersSubscriptionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UsersSubscription usersSubscription) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Subjects subjectsSubjectid = usersSubscription.getSubjectsSubjectid();
            if (subjectsSubjectid != null) {
                subjectsSubjectid = em.getReference(subjectsSubjectid.getClass(), subjectsSubjectid.getSubjectid());
                usersSubscription.setSubjectsSubjectid(subjectsSubjectid);
            }
            em.persist(usersSubscription);
            if (subjectsSubjectid != null) {
                subjectsSubjectid.getUsersSubscriptionList().add(usersSubscription);
                subjectsSubjectid = em.merge(subjectsSubjectid);
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

    public void edit(UsersSubscription usersSubscription) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            UsersSubscription persistentUsersSubscription = em.find(UsersSubscription.class, usersSubscription.getSubscriptionid());
            Subjects subjectsSubjectidOld = persistentUsersSubscription.getSubjectsSubjectid();
            Subjects subjectsSubjectidNew = usersSubscription.getSubjectsSubjectid();
            if (subjectsSubjectidNew != null) {
                subjectsSubjectidNew = em.getReference(subjectsSubjectidNew.getClass(), subjectsSubjectidNew.getSubjectid());
                usersSubscription.setSubjectsSubjectid(subjectsSubjectidNew);
            }
            usersSubscription = em.merge(usersSubscription);
            if (subjectsSubjectidOld != null && !subjectsSubjectidOld.equals(subjectsSubjectidNew)) {
                subjectsSubjectidOld.getUsersSubscriptionList().remove(usersSubscription);
                subjectsSubjectidOld = em.merge(subjectsSubjectidOld);
            }
            if (subjectsSubjectidNew != null && !subjectsSubjectidNew.equals(subjectsSubjectidOld)) {
                subjectsSubjectidNew.getUsersSubscriptionList().add(usersSubscription);
                subjectsSubjectidNew = em.merge(subjectsSubjectidNew);
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
                Integer id = usersSubscription.getSubscriptionid();
                if (findUsersSubscription(id) == null) {
                    throw new NonexistentEntityException("The usersSubscription with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            UsersSubscription usersSubscription;
            try {
                usersSubscription = em.getReference(UsersSubscription.class, id);
                usersSubscription.getSubscriptionid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usersSubscription with id " + id + " no longer exists.", enfe);
            }
            Subjects subjectsSubjectid = usersSubscription.getSubjectsSubjectid();
            if (subjectsSubjectid != null) {
                subjectsSubjectid.getUsersSubscriptionList().remove(usersSubscription);
                subjectsSubjectid = em.merge(subjectsSubjectid);
            }
            em.remove(usersSubscription);
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

    public List<UsersSubscription> findUsersSubscriptionEntities() {
        return findUsersSubscriptionEntities(true, -1, -1);
    }

    public List<UsersSubscription> findUsersSubscriptionEntities(int maxResults, int firstResult) {
        return findUsersSubscriptionEntities(false, maxResults, firstResult);
    }

    private List<UsersSubscription> findUsersSubscriptionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UsersSubscription.class));
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

    public UsersSubscription findUsersSubscription(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UsersSubscription.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersSubscriptionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UsersSubscription> rt = cq.from(UsersSubscription.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
