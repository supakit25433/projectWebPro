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
import jpaClasses.Quizrecord;

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

    public void create(Quizrecord quizrecord) throws IllegalOrphanException, RollbackFailureException, Exception {
        List<String> illegalOrphanMessages = null;
        Quizes quizesQuizidOrphanCheck = quizrecord.getQuizesQuizid();
        if (quizesQuizidOrphanCheck != null) {
            Quizrecord oldQuizrecordOfQuizesQuizid = quizesQuizidOrphanCheck.getQuizrecord();
            if (oldQuizrecordOfQuizesQuizid != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Quizes " + quizesQuizidOrphanCheck + " already has an item of type Quizrecord whose quizesQuizid column cannot be null. Please make another selection for the quizesQuizid field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Quizes quizesQuizid = quizrecord.getQuizesQuizid();
            if (quizesQuizid != null) {
                quizesQuizid = em.getReference(quizesQuizid.getClass(), quizesQuizid.getQuizid());
                quizrecord.setQuizesQuizid(quizesQuizid);
            }
            em.persist(quizrecord);
            if (quizesQuizid != null) {
                quizesQuizid.setQuizrecord(quizrecord);
                quizesQuizid = em.merge(quizesQuizid);
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

    public void edit(Quizrecord quizrecord) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Quizrecord persistentQuizrecord = em.find(Quizrecord.class, quizrecord.getQuizrecordid());
            Quizes quizesQuizidOld = persistentQuizrecord.getQuizesQuizid();
            Quizes quizesQuizidNew = quizrecord.getQuizesQuizid();
            List<String> illegalOrphanMessages = null;
            if (quizesQuizidNew != null && !quizesQuizidNew.equals(quizesQuizidOld)) {
                Quizrecord oldQuizrecordOfQuizesQuizid = quizesQuizidNew.getQuizrecord();
                if (oldQuizrecordOfQuizesQuizid != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Quizes " + quizesQuizidNew + " already has an item of type Quizrecord whose quizesQuizid column cannot be null. Please make another selection for the quizesQuizid field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (quizesQuizidNew != null) {
                quizesQuizidNew = em.getReference(quizesQuizidNew.getClass(), quizesQuizidNew.getQuizid());
                quizrecord.setQuizesQuizid(quizesQuizidNew);
            }
            quizrecord = em.merge(quizrecord);
            if (quizesQuizidOld != null && !quizesQuizidOld.equals(quizesQuizidNew)) {
                quizesQuizidOld.setQuizrecord(null);
                quizesQuizidOld = em.merge(quizesQuizidOld);
            }
            if (quizesQuizidNew != null && !quizesQuizidNew.equals(quizesQuizidOld)) {
                quizesQuizidNew.setQuizrecord(quizrecord);
                quizesQuizidNew = em.merge(quizesQuizidNew);
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
            Quizes quizesQuizid = quizrecord.getQuizesQuizid();
            if (quizesQuizid != null) {
                quizesQuizid.setQuizrecord(null);
                quizesQuizid = em.merge(quizesQuizid);
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
