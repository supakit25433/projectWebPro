/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpaClasses.Choices;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpaClasses.Questions;
import jpaClasses.StudentsChoice;
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
public class ChoicesJpaController implements Serializable {

    public ChoicesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Choices choices) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Questions questionsQuestionid = choices.getQuestionsQuestionid();
            if (questionsQuestionid != null) {
                questionsQuestionid = em.getReference(questionsQuestionid.getClass(), questionsQuestionid.getQuestionid());
                choices.setQuestionsQuestionid(questionsQuestionid);
            }
            StudentsChoice studentsChoice = choices.getStudentsChoice();
            if (studentsChoice != null) {
                studentsChoice = em.getReference(studentsChoice.getClass(), studentsChoice.getStudentchoiceid());
                choices.setStudentsChoice(studentsChoice);
            }
            em.persist(choices);
            if (questionsQuestionid != null) {
                questionsQuestionid.getChoicesList().add(choices);
                questionsQuestionid = em.merge(questionsQuestionid);
            }
            if (studentsChoice != null) {
                Choices oldChoicesChoiceidOfStudentsChoice = studentsChoice.getChoicesChoiceid();
                if (oldChoicesChoiceidOfStudentsChoice != null) {
                    oldChoicesChoiceidOfStudentsChoice.setStudentsChoice(null);
                    oldChoicesChoiceidOfStudentsChoice = em.merge(oldChoicesChoiceidOfStudentsChoice);
                }
                studentsChoice.setChoicesChoiceid(choices);
                studentsChoice = em.merge(studentsChoice);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findChoices(choices.getChoiceid()) != null) {
                throw new PreexistingEntityException("Choices " + choices + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Choices choices) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Choices persistentChoices = em.find(Choices.class, choices.getChoiceid());
            Questions questionsQuestionidOld = persistentChoices.getQuestionsQuestionid();
            Questions questionsQuestionidNew = choices.getQuestionsQuestionid();
            StudentsChoice studentsChoiceOld = persistentChoices.getStudentsChoice();
            StudentsChoice studentsChoiceNew = choices.getStudentsChoice();
            List<String> illegalOrphanMessages = null;
            if (studentsChoiceOld != null && !studentsChoiceOld.equals(studentsChoiceNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain StudentsChoice " + studentsChoiceOld + " since its choicesChoiceid field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (questionsQuestionidNew != null) {
                questionsQuestionidNew = em.getReference(questionsQuestionidNew.getClass(), questionsQuestionidNew.getQuestionid());
                choices.setQuestionsQuestionid(questionsQuestionidNew);
            }
            if (studentsChoiceNew != null) {
                studentsChoiceNew = em.getReference(studentsChoiceNew.getClass(), studentsChoiceNew.getStudentchoiceid());
                choices.setStudentsChoice(studentsChoiceNew);
            }
            choices = em.merge(choices);
            if (questionsQuestionidOld != null && !questionsQuestionidOld.equals(questionsQuestionidNew)) {
                questionsQuestionidOld.getChoicesList().remove(choices);
                questionsQuestionidOld = em.merge(questionsQuestionidOld);
            }
            if (questionsQuestionidNew != null && !questionsQuestionidNew.equals(questionsQuestionidOld)) {
                questionsQuestionidNew.getChoicesList().add(choices);
                questionsQuestionidNew = em.merge(questionsQuestionidNew);
            }
            if (studentsChoiceNew != null && !studentsChoiceNew.equals(studentsChoiceOld)) {
                Choices oldChoicesChoiceidOfStudentsChoice = studentsChoiceNew.getChoicesChoiceid();
                if (oldChoicesChoiceidOfStudentsChoice != null) {
                    oldChoicesChoiceidOfStudentsChoice.setStudentsChoice(null);
                    oldChoicesChoiceidOfStudentsChoice = em.merge(oldChoicesChoiceidOfStudentsChoice);
                }
                studentsChoiceNew.setChoicesChoiceid(choices);
                studentsChoiceNew = em.merge(studentsChoiceNew);
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
                Integer id = choices.getChoiceid();
                if (findChoices(id) == null) {
                    throw new NonexistentEntityException("The choices with id " + id + " no longer exists.");
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
            Choices choices;
            try {
                choices = em.getReference(Choices.class, id);
                choices.getChoiceid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The choices with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            StudentsChoice studentsChoiceOrphanCheck = choices.getStudentsChoice();
            if (studentsChoiceOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Choices (" + choices + ") cannot be destroyed since the StudentsChoice " + studentsChoiceOrphanCheck + " in its studentsChoice field has a non-nullable choicesChoiceid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Questions questionsQuestionid = choices.getQuestionsQuestionid();
            if (questionsQuestionid != null) {
                questionsQuestionid.getChoicesList().remove(choices);
                questionsQuestionid = em.merge(questionsQuestionid);
            }
            em.remove(choices);
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

    public List<Choices> findChoicesEntities() {
        return findChoicesEntities(true, -1, -1);
    }

    public List<Choices> findChoicesEntities(int maxResults, int firstResult) {
        return findChoicesEntities(false, maxResults, firstResult);
    }

    private List<Choices> findChoicesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Choices.class));
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

    public Choices findChoices(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Choices.class, id);
        } finally {
            em.close();
        }
    }

    public int getChoicesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Choices> rt = cq.from(Choices.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
