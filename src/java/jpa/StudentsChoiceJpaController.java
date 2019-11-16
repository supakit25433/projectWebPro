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
import Entities.Choices;
import Entities.StudentsChoice;
import Entities.Users;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;
import jpa.exceptions.RollbackFailureException;

/**
 *
 * @author Gamer
 */
public class StudentsChoiceJpaController implements Serializable {

    public StudentsChoiceJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StudentsChoice studentsChoice) throws IllegalOrphanException, PreexistingEntityException, RollbackFailureException, Exception {
        List<String> illegalOrphanMessages = null;
        Choices choicesChoiceidOrphanCheck = studentsChoice.getChoicesChoiceid();
        if (choicesChoiceidOrphanCheck != null) {
            StudentsChoice oldStudentsChoiceOfChoicesChoiceid = choicesChoiceidOrphanCheck.getStudentsChoice();
            if (oldStudentsChoiceOfChoicesChoiceid != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Choices " + choicesChoiceidOrphanCheck + " already has an item of type StudentsChoice whose choicesChoiceid column cannot be null. Please make another selection for the choicesChoiceid field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Choices choicesChoiceid = studentsChoice.getChoicesChoiceid();
            if (choicesChoiceid != null) {
                choicesChoiceid = em.getReference(choicesChoiceid.getClass(), choicesChoiceid.getChoiceid());
                studentsChoice.setChoicesChoiceid(choicesChoiceid);
            }
            Users usersUserid = studentsChoice.getUsersUserid();
            if (usersUserid != null) {
                usersUserid = em.getReference(usersUserid.getClass(), usersUserid.getUserid());
                studentsChoice.setUsersUserid(usersUserid);
            }
            em.persist(studentsChoice);
            if (choicesChoiceid != null) {
                choicesChoiceid.setStudentsChoice(studentsChoice);
                choicesChoiceid = em.merge(choicesChoiceid);
            }
            if (usersUserid != null) {
                usersUserid.getStudentsChoiceList().add(studentsChoice);
                usersUserid = em.merge(usersUserid);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findStudentsChoice(studentsChoice.getStudentchoiceid()) != null) {
                throw new PreexistingEntityException("StudentsChoice " + studentsChoice + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StudentsChoice studentsChoice) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            StudentsChoice persistentStudentsChoice = em.find(StudentsChoice.class, studentsChoice.getStudentchoiceid());
            Choices choicesChoiceidOld = persistentStudentsChoice.getChoicesChoiceid();
            Choices choicesChoiceidNew = studentsChoice.getChoicesChoiceid();
            Users usersUseridOld = persistentStudentsChoice.getUsersUserid();
            Users usersUseridNew = studentsChoice.getUsersUserid();
            List<String> illegalOrphanMessages = null;
            if (choicesChoiceidNew != null && !choicesChoiceidNew.equals(choicesChoiceidOld)) {
                StudentsChoice oldStudentsChoiceOfChoicesChoiceid = choicesChoiceidNew.getStudentsChoice();
                if (oldStudentsChoiceOfChoicesChoiceid != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Choices " + choicesChoiceidNew + " already has an item of type StudentsChoice whose choicesChoiceid column cannot be null. Please make another selection for the choicesChoiceid field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (choicesChoiceidNew != null) {
                choicesChoiceidNew = em.getReference(choicesChoiceidNew.getClass(), choicesChoiceidNew.getChoiceid());
                studentsChoice.setChoicesChoiceid(choicesChoiceidNew);
            }
            if (usersUseridNew != null) {
                usersUseridNew = em.getReference(usersUseridNew.getClass(), usersUseridNew.getUserid());
                studentsChoice.setUsersUserid(usersUseridNew);
            }
            studentsChoice = em.merge(studentsChoice);
            if (choicesChoiceidOld != null && !choicesChoiceidOld.equals(choicesChoiceidNew)) {
                choicesChoiceidOld.setStudentsChoice(null);
                choicesChoiceidOld = em.merge(choicesChoiceidOld);
            }
            if (choicesChoiceidNew != null && !choicesChoiceidNew.equals(choicesChoiceidOld)) {
                choicesChoiceidNew.setStudentsChoice(studentsChoice);
                choicesChoiceidNew = em.merge(choicesChoiceidNew);
            }
            if (usersUseridOld != null && !usersUseridOld.equals(usersUseridNew)) {
                usersUseridOld.getStudentsChoiceList().remove(studentsChoice);
                usersUseridOld = em.merge(usersUseridOld);
            }
            if (usersUseridNew != null && !usersUseridNew.equals(usersUseridOld)) {
                usersUseridNew.getStudentsChoiceList().add(studentsChoice);
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
                Integer id = studentsChoice.getStudentchoiceid();
                if (findStudentsChoice(id) == null) {
                    throw new NonexistentEntityException("The studentsChoice with id " + id + " no longer exists.");
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
            StudentsChoice studentsChoice;
            try {
                studentsChoice = em.getReference(StudentsChoice.class, id);
                studentsChoice.getStudentchoiceid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The studentsChoice with id " + id + " no longer exists.", enfe);
            }
            Choices choicesChoiceid = studentsChoice.getChoicesChoiceid();
            if (choicesChoiceid != null) {
                choicesChoiceid.setStudentsChoice(null);
                choicesChoiceid = em.merge(choicesChoiceid);
            }
            Users usersUserid = studentsChoice.getUsersUserid();
            if (usersUserid != null) {
                usersUserid.getStudentsChoiceList().remove(studentsChoice);
                usersUserid = em.merge(usersUserid);
            }
            em.remove(studentsChoice);
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

    public List<StudentsChoice> findStudentsChoiceEntities() {
        return findStudentsChoiceEntities(true, -1, -1);
    }

    public List<StudentsChoice> findStudentsChoiceEntities(int maxResults, int firstResult) {
        return findStudentsChoiceEntities(false, maxResults, firstResult);
    }

    private List<StudentsChoice> findStudentsChoiceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StudentsChoice.class));
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

    public StudentsChoice findStudentsChoice(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StudentsChoice.class, id);
        } finally {
            em.close();
        }
    }

    public int getStudentsChoiceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StudentsChoice> rt = cq.from(StudentsChoice.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
