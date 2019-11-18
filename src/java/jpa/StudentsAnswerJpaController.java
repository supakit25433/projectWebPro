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
import jpaClasses.Longanswer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.RollbackFailureException;
import jpaClasses.StudentsAnswer;

/**
 *
 * @author Gamer
 */
public class StudentsAnswerJpaController implements Serializable {

    public StudentsAnswerJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StudentsAnswer studentsAnswer) throws IllegalOrphanException, RollbackFailureException, Exception {
        List<String> illegalOrphanMessages = null;
        Longanswer longanswerAnsweridOrphanCheck = studentsAnswer.getLonganswerAnswerid();
        if (longanswerAnsweridOrphanCheck != null) {
            StudentsAnswer oldStudentsAnswerOfLonganswerAnswerid = longanswerAnsweridOrphanCheck.getStudentsAnswer();
            if (oldStudentsAnswerOfLonganswerAnswerid != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Longanswer " + longanswerAnsweridOrphanCheck + " already has an item of type StudentsAnswer whose longanswerAnswerid column cannot be null. Please make another selection for the longanswerAnswerid field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Longanswer longanswerAnswerid = studentsAnswer.getLonganswerAnswerid();
            if (longanswerAnswerid != null) {
                longanswerAnswerid = em.getReference(longanswerAnswerid.getClass(), longanswerAnswerid.getAnswerid());
                studentsAnswer.setLonganswerAnswerid(longanswerAnswerid);
            }
            em.persist(studentsAnswer);
            if (longanswerAnswerid != null) {
                longanswerAnswerid.setStudentsAnswer(studentsAnswer);
                longanswerAnswerid = em.merge(longanswerAnswerid);
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

    public void edit(StudentsAnswer studentsAnswer) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            StudentsAnswer persistentStudentsAnswer = em.find(StudentsAnswer.class, studentsAnswer.getStudentanswerid());
            Longanswer longanswerAnsweridOld = persistentStudentsAnswer.getLonganswerAnswerid();
            Longanswer longanswerAnsweridNew = studentsAnswer.getLonganswerAnswerid();
            List<String> illegalOrphanMessages = null;
            if (longanswerAnsweridNew != null && !longanswerAnsweridNew.equals(longanswerAnsweridOld)) {
                StudentsAnswer oldStudentsAnswerOfLonganswerAnswerid = longanswerAnsweridNew.getStudentsAnswer();
                if (oldStudentsAnswerOfLonganswerAnswerid != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Longanswer " + longanswerAnsweridNew + " already has an item of type StudentsAnswer whose longanswerAnswerid column cannot be null. Please make another selection for the longanswerAnswerid field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (longanswerAnsweridNew != null) {
                longanswerAnsweridNew = em.getReference(longanswerAnsweridNew.getClass(), longanswerAnsweridNew.getAnswerid());
                studentsAnswer.setLonganswerAnswerid(longanswerAnsweridNew);
            }
            studentsAnswer = em.merge(studentsAnswer);
            if (longanswerAnsweridOld != null && !longanswerAnsweridOld.equals(longanswerAnsweridNew)) {
                longanswerAnsweridOld.setStudentsAnswer(null);
                longanswerAnsweridOld = em.merge(longanswerAnsweridOld);
            }
            if (longanswerAnsweridNew != null && !longanswerAnsweridNew.equals(longanswerAnsweridOld)) {
                longanswerAnsweridNew.setStudentsAnswer(studentsAnswer);
                longanswerAnsweridNew = em.merge(longanswerAnsweridNew);
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
                Integer id = studentsAnswer.getStudentanswerid();
                if (findStudentsAnswer(id) == null) {
                    throw new NonexistentEntityException("The studentsAnswer with id " + id + " no longer exists.");
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
            StudentsAnswer studentsAnswer;
            try {
                studentsAnswer = em.getReference(StudentsAnswer.class, id);
                studentsAnswer.getStudentanswerid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The studentsAnswer with id " + id + " no longer exists.", enfe);
            }
            Longanswer longanswerAnswerid = studentsAnswer.getLonganswerAnswerid();
            if (longanswerAnswerid != null) {
                longanswerAnswerid.setStudentsAnswer(null);
                longanswerAnswerid = em.merge(longanswerAnswerid);
            }
            em.remove(studentsAnswer);
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

    public List<StudentsAnswer> findStudentsAnswerEntities() {
        return findStudentsAnswerEntities(true, -1, -1);
    }

    public List<StudentsAnswer> findStudentsAnswerEntities(int maxResults, int firstResult) {
        return findStudentsAnswerEntities(false, maxResults, firstResult);
    }

    private List<StudentsAnswer> findStudentsAnswerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StudentsAnswer.class));
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

    public StudentsAnswer findStudentsAnswer(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StudentsAnswer.class, id);
        } finally {
            em.close();
        }
    }

    public int getStudentsAnswerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StudentsAnswer> rt = cq.from(StudentsAnswer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
