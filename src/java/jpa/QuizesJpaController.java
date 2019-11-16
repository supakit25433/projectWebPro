/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpaClasses.Quizes;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpaClasses.Subjects;
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
public class QuizesJpaController implements Serializable {

    public QuizesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Quizes quizes) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Subjects subjectsSubjectid = quizes.getSubjectsSubjectid();
            if (subjectsSubjectid != null) {
                subjectsSubjectid = em.getReference(subjectsSubjectid.getClass(), subjectsSubjectid.getSubjectid());
                quizes.setSubjectsSubjectid(subjectsSubjectid);
            }
            em.persist(quizes);
            if (subjectsSubjectid != null) {
                subjectsSubjectid.getQuizesList().add(quizes);
                subjectsSubjectid = em.merge(subjectsSubjectid);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findQuizes(quizes.getQuizid()) != null) {
                throw new PreexistingEntityException("Quizes " + quizes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Quizes quizes) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Quizes persistentQuizes = em.find(Quizes.class, quizes.getQuizid());
            Subjects subjectsSubjectidOld = persistentQuizes.getSubjectsSubjectid();
            Subjects subjectsSubjectidNew = quizes.getSubjectsSubjectid();
            if (subjectsSubjectidNew != null) {
                subjectsSubjectidNew = em.getReference(subjectsSubjectidNew.getClass(), subjectsSubjectidNew.getSubjectid());
                quizes.setSubjectsSubjectid(subjectsSubjectidNew);
            }
            quizes = em.merge(quizes);
            if (subjectsSubjectidOld != null && !subjectsSubjectidOld.equals(subjectsSubjectidNew)) {
                subjectsSubjectidOld.getQuizesList().remove(quizes);
                subjectsSubjectidOld = em.merge(subjectsSubjectidOld);
            }
            if (subjectsSubjectidNew != null && !subjectsSubjectidNew.equals(subjectsSubjectidOld)) {
                subjectsSubjectidNew.getQuizesList().add(quizes);
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
                Integer id = quizes.getQuizid();
                if (findQuizes(id) == null) {
                    throw new NonexistentEntityException("The quizes with id " + id + " no longer exists.");
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
            Quizes quizes;
            try {
                quizes = em.getReference(Quizes.class, id);
                quizes.getQuizid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The quizes with id " + id + " no longer exists.", enfe);
            }
            Subjects subjectsSubjectid = quizes.getSubjectsSubjectid();
            if (subjectsSubjectid != null) {
                subjectsSubjectid.getQuizesList().remove(quizes);
                subjectsSubjectid = em.merge(subjectsSubjectid);
            }
            em.remove(quizes);
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

    public List<Quizes> findQuizesEntities() {
        return findQuizesEntities(true, -1, -1);
    }

    public List<Quizes> findQuizesEntities(int maxResults, int firstResult) {
        return findQuizesEntities(false, maxResults, firstResult);
    }

    private List<Quizes> findQuizesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Quizes.class));
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

    public Quizes findQuizes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Quizes.class, id);
        } finally {
            em.close();
        }
    }

    public int getQuizesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Quizes> rt = cq.from(Quizes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
