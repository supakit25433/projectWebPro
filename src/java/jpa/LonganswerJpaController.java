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
import jpaClasses.Questions;
import jpaClasses.Users;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.RollbackFailureException;
import jpaClasses.Longanswer;

/**
 *
 * @author Gamer
 */
public class LonganswerJpaController implements Serializable {

    public LonganswerJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Longanswer longanswer) throws IllegalOrphanException, RollbackFailureException, Exception {
        List<String> illegalOrphanMessages = null;
        Questions questionsQuestionidOrphanCheck = longanswer.getQuestionsQuestionid();
        if (questionsQuestionidOrphanCheck != null) {
            Longanswer oldLonganswerOfQuestionsQuestionid = questionsQuestionidOrphanCheck.getLonganswer();
            if (oldLonganswerOfQuestionsQuestionid != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Questions " + questionsQuestionidOrphanCheck + " already has an item of type Longanswer whose questionsQuestionid column cannot be null. Please make another selection for the questionsQuestionid field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Questions questionsQuestionid = longanswer.getQuestionsQuestionid();
            if (questionsQuestionid != null) {
                questionsQuestionid = em.getReference(questionsQuestionid.getClass(), questionsQuestionid.getQuestionid());
                longanswer.setQuestionsQuestionid(questionsQuestionid);
            }
            Users usersUserid = longanswer.getUsersUserid();
            if (usersUserid != null) {
                usersUserid = em.getReference(usersUserid.getClass(), usersUserid.getUserid());
                longanswer.setUsersUserid(usersUserid);
            }
            em.persist(longanswer);
            if (questionsQuestionid != null) {
                questionsQuestionid.setLonganswer(longanswer);
                questionsQuestionid = em.merge(questionsQuestionid);
            }
            if (usersUserid != null) {
                usersUserid.getLonganswerList().add(longanswer);
                usersUserid = em.merge(usersUserid);
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

    public void edit(Longanswer longanswer) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Longanswer persistentLonganswer = em.find(Longanswer.class, longanswer.getAnswerid());
            Questions questionsQuestionidOld = persistentLonganswer.getQuestionsQuestionid();
            Questions questionsQuestionidNew = longanswer.getQuestionsQuestionid();
            Users usersUseridOld = persistentLonganswer.getUsersUserid();
            Users usersUseridNew = longanswer.getUsersUserid();
            List<String> illegalOrphanMessages = null;
            if (questionsQuestionidNew != null && !questionsQuestionidNew.equals(questionsQuestionidOld)) {
                Longanswer oldLonganswerOfQuestionsQuestionid = questionsQuestionidNew.getLonganswer();
                if (oldLonganswerOfQuestionsQuestionid != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Questions " + questionsQuestionidNew + " already has an item of type Longanswer whose questionsQuestionid column cannot be null. Please make another selection for the questionsQuestionid field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (questionsQuestionidNew != null) {
                questionsQuestionidNew = em.getReference(questionsQuestionidNew.getClass(), questionsQuestionidNew.getQuestionid());
                longanswer.setQuestionsQuestionid(questionsQuestionidNew);
            }
            if (usersUseridNew != null) {
                usersUseridNew = em.getReference(usersUseridNew.getClass(), usersUseridNew.getUserid());
                longanswer.setUsersUserid(usersUseridNew);
            }
            longanswer = em.merge(longanswer);
            if (questionsQuestionidOld != null && !questionsQuestionidOld.equals(questionsQuestionidNew)) {
                questionsQuestionidOld.setLonganswer(null);
                questionsQuestionidOld = em.merge(questionsQuestionidOld);
            }
            if (questionsQuestionidNew != null && !questionsQuestionidNew.equals(questionsQuestionidOld)) {
                questionsQuestionidNew.setLonganswer(longanswer);
                questionsQuestionidNew = em.merge(questionsQuestionidNew);
            }
            if (usersUseridOld != null && !usersUseridOld.equals(usersUseridNew)) {
                usersUseridOld.getLonganswerList().remove(longanswer);
                usersUseridOld = em.merge(usersUseridOld);
            }
            if (usersUseridNew != null && !usersUseridNew.equals(usersUseridOld)) {
                usersUseridNew.getLonganswerList().add(longanswer);
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
                Integer id = longanswer.getAnswerid();
                if (findLonganswer(id) == null) {
                    throw new NonexistentEntityException("The longanswer with id " + id + " no longer exists.");
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
            Longanswer longanswer;
            try {
                longanswer = em.getReference(Longanswer.class, id);
                longanswer.getAnswerid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The longanswer with id " + id + " no longer exists.", enfe);
            }
            Questions questionsQuestionid = longanswer.getQuestionsQuestionid();
            if (questionsQuestionid != null) {
                questionsQuestionid.setLonganswer(null);
                questionsQuestionid = em.merge(questionsQuestionid);
            }
            Users usersUserid = longanswer.getUsersUserid();
            if (usersUserid != null) {
                usersUserid.getLonganswerList().remove(longanswer);
                usersUserid = em.merge(usersUserid);
            }
            em.remove(longanswer);
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

    public List<Longanswer> findLonganswerEntities() {
        return findLonganswerEntities(true, -1, -1);
    }

    public List<Longanswer> findLonganswerEntities(int maxResults, int firstResult) {
        return findLonganswerEntities(false, maxResults, firstResult);
    }

    private List<Longanswer> findLonganswerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Longanswer.class));
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

    public Longanswer findLonganswer(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Longanswer.class, id);
        } finally {
            em.close();
        }
    }

    public int getLonganswerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Longanswer> rt = cq.from(Longanswer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
