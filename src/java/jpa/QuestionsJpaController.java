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
import jpaClasses.Choices;
import jpaClasses.Questions;
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
public class QuestionsJpaController implements Serializable {

    public QuestionsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Questions questions) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (questions.getChoicesList() == null) {
            questions.setChoicesList(new ArrayList<Choices>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Longanswer longanswer = questions.getLonganswer();
            if (longanswer != null) {
                longanswer = em.getReference(longanswer.getClass(), longanswer.getAnswerid());
                questions.setLonganswer(longanswer);
            }
            List<Choices> attachedChoicesList = new ArrayList<Choices>();
            for (Choices choicesListChoicesToAttach : questions.getChoicesList()) {
                choicesListChoicesToAttach = em.getReference(choicesListChoicesToAttach.getClass(), choicesListChoicesToAttach.getChoiceid());
                attachedChoicesList.add(choicesListChoicesToAttach);
            }
            questions.setChoicesList(attachedChoicesList);
            em.persist(questions);
            if (longanswer != null) {
                Questions oldQuestionsQuestionidOfLonganswer = longanswer.getQuestionsQuestionid();
                if (oldQuestionsQuestionidOfLonganswer != null) {
                    oldQuestionsQuestionidOfLonganswer.setLonganswer(null);
                    oldQuestionsQuestionidOfLonganswer = em.merge(oldQuestionsQuestionidOfLonganswer);
                }
                longanswer.setQuestionsQuestionid(questions);
                longanswer = em.merge(longanswer);
            }
            for (Choices choicesListChoices : questions.getChoicesList()) {
                Questions oldQuestionsQuestionidOfChoicesListChoices = choicesListChoices.getQuestionsQuestionid();
                choicesListChoices.setQuestionsQuestionid(questions);
                choicesListChoices = em.merge(choicesListChoices);
                if (oldQuestionsQuestionidOfChoicesListChoices != null) {
                    oldQuestionsQuestionidOfChoicesListChoices.getChoicesList().remove(choicesListChoices);
                    oldQuestionsQuestionidOfChoicesListChoices = em.merge(oldQuestionsQuestionidOfChoicesListChoices);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findQuestions(questions.getQuestionid()) != null) {
                throw new PreexistingEntityException("Questions " + questions + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Questions questions) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Questions persistentQuestions = em.find(Questions.class, questions.getQuestionid());
            Longanswer longanswerOld = persistentQuestions.getLonganswer();
            Longanswer longanswerNew = questions.getLonganswer();
            List<Choices> choicesListOld = persistentQuestions.getChoicesList();
            List<Choices> choicesListNew = questions.getChoicesList();
            List<String> illegalOrphanMessages = null;
            if (longanswerOld != null && !longanswerOld.equals(longanswerNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Longanswer " + longanswerOld + " since its questionsQuestionid field is not nullable.");
            }
            for (Choices choicesListOldChoices : choicesListOld) {
                if (!choicesListNew.contains(choicesListOldChoices)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Choices " + choicesListOldChoices + " since its questionsQuestionid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (longanswerNew != null) {
                longanswerNew = em.getReference(longanswerNew.getClass(), longanswerNew.getAnswerid());
                questions.setLonganswer(longanswerNew);
            }
            List<Choices> attachedChoicesListNew = new ArrayList<Choices>();
            for (Choices choicesListNewChoicesToAttach : choicesListNew) {
                choicesListNewChoicesToAttach = em.getReference(choicesListNewChoicesToAttach.getClass(), choicesListNewChoicesToAttach.getChoiceid());
                attachedChoicesListNew.add(choicesListNewChoicesToAttach);
            }
            choicesListNew = attachedChoicesListNew;
            questions.setChoicesList(choicesListNew);
            questions = em.merge(questions);
            if (longanswerNew != null && !longanswerNew.equals(longanswerOld)) {
                Questions oldQuestionsQuestionidOfLonganswer = longanswerNew.getQuestionsQuestionid();
                if (oldQuestionsQuestionidOfLonganswer != null) {
                    oldQuestionsQuestionidOfLonganswer.setLonganswer(null);
                    oldQuestionsQuestionidOfLonganswer = em.merge(oldQuestionsQuestionidOfLonganswer);
                }
                longanswerNew.setQuestionsQuestionid(questions);
                longanswerNew = em.merge(longanswerNew);
            }
            for (Choices choicesListNewChoices : choicesListNew) {
                if (!choicesListOld.contains(choicesListNewChoices)) {
                    Questions oldQuestionsQuestionidOfChoicesListNewChoices = choicesListNewChoices.getQuestionsQuestionid();
                    choicesListNewChoices.setQuestionsQuestionid(questions);
                    choicesListNewChoices = em.merge(choicesListNewChoices);
                    if (oldQuestionsQuestionidOfChoicesListNewChoices != null && !oldQuestionsQuestionidOfChoicesListNewChoices.equals(questions)) {
                        oldQuestionsQuestionidOfChoicesListNewChoices.getChoicesList().remove(choicesListNewChoices);
                        oldQuestionsQuestionidOfChoicesListNewChoices = em.merge(oldQuestionsQuestionidOfChoicesListNewChoices);
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
                Integer id = questions.getQuestionid();
                if (findQuestions(id) == null) {
                    throw new NonexistentEntityException("The questions with id " + id + " no longer exists.");
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
            Questions questions;
            try {
                questions = em.getReference(Questions.class, id);
                questions.getQuestionid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The questions with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Longanswer longanswerOrphanCheck = questions.getLonganswer();
            if (longanswerOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Questions (" + questions + ") cannot be destroyed since the Longanswer " + longanswerOrphanCheck + " in its longanswer field has a non-nullable questionsQuestionid field.");
            }
            List<Choices> choicesListOrphanCheck = questions.getChoicesList();
            for (Choices choicesListOrphanCheckChoices : choicesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Questions (" + questions + ") cannot be destroyed since the Choices " + choicesListOrphanCheckChoices + " in its choicesList field has a non-nullable questionsQuestionid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(questions);
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

    public List<Questions> findQuestionsEntities() {
        return findQuestionsEntities(true, -1, -1);
    }

    public List<Questions> findQuestionsEntities(int maxResults, int firstResult) {
        return findQuestionsEntities(false, maxResults, firstResult);
    }

    private List<Questions> findQuestionsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Questions.class));
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

    public Questions findQuestions(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Questions.class, id);
        } finally {
            em.close();
        }
    }

    public int getQuestionsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Questions> rt = cq.from(Questions.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
