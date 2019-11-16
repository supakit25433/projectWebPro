/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpaClasses.Quizrecord;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpaClasses.Users;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;
import jpa.exceptions.RollbackFailureException;

/**
 *
 * @author Gamer
 */
public class QuizrecordJpaController implements Serializable {

    public QuizrecordJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Quizrecord quizrecord) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Users usersUserid = quizrecord.getUsersUserid();
            if (usersUserid != null) {
                usersUserid = em.getReference(usersUserid.getClass(), usersUserid.getUserid());
                quizrecord.setUsersUserid(usersUserid);
            }
            em.persist(quizrecord);
            if (usersUserid != null) {
                usersUserid.getQuizrecordList().add(quizrecord);
                usersUserid = em.merge(usersUserid);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findQuizrecord(quizrecord.getQuizrecordid()) != null) {
                throw new PreexistingEntityException("Quizrecord " + quizrecord + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Quizrecord quizrecord) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Quizrecord persistentQuizrecord = em.find(Quizrecord.class, quizrecord.getQuizrecordid());
            Users usersUseridOld = persistentQuizrecord.getUsersUserid();
            Users usersUseridNew = quizrecord.getUsersUserid();
            if (usersUseridNew != null) {
                usersUseridNew = em.getReference(usersUseridNew.getClass(), usersUseridNew.getUserid());
                quizrecord.setUsersUserid(usersUseridNew);
            }
            quizrecord = em.merge(quizrecord);
            if (usersUseridOld != null && !usersUseridOld.equals(usersUseridNew)) {
                usersUseridOld.getQuizrecordList().remove(quizrecord);
                usersUseridOld = em.merge(usersUseridOld);
            }
            if (usersUseridNew != null && !usersUseridNew.equals(usersUseridOld)) {
                usersUseridNew.getQuizrecordList().add(quizrecord);
                usersUseridNew = em.merge(usersUseridNew);
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
                Integer id = quizrecord.getQuizrecordid();
                if (findQuizrecord(id) == null) {
                    throw new NonexistentEntityException("The quizrecord with id " + id + " no longer exists.");
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
            Quizrecord quizrecord;
            try {
                quizrecord = em.getReference(Quizrecord.class, id);
                quizrecord.getQuizrecordid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The quizrecord with id " + id + " no longer exists.", enfe);
            }
            Users usersUserid = quizrecord.getUsersUserid();
            if (usersUserid != null) {
                usersUserid.getQuizrecordList().remove(quizrecord);
                usersUserid = em.merge(usersUserid);
            }
            em.remove(quizrecord);
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

    public List<Quizrecord> findQuizrecordEntities() {
        return findQuizrecordEntities(true, -1, -1);
    }

    public List<Quizrecord> findQuizrecordEntities(int maxResults, int firstResult) {
        return findQuizrecordEntities(false, maxResults, firstResult);
    }

    private List<Quizrecord> findQuizrecordEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Quizrecord.class));
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

    public Quizrecord findQuizrecord(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Quizrecord.class, id);
        } finally {
            em.close();
        }
    }

    public int getQuizrecordCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Quizrecord> rt = cq.from(Quizrecord.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
