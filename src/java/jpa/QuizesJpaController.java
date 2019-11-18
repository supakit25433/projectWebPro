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
import jpaClasses.Quizrecord;
import jpaClasses.Subjects;
import jpaClasses.Questions;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.RollbackFailureException;
import jpaClasses.Quizes;

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

    public void create(Quizes quizes) throws RollbackFailureException, Exception {
        if (quizes.getQuestionsList() == null) {
            quizes.setQuestionsList(new ArrayList<Questions>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Quizrecord quizrecord = quizes.getQuizrecord();
            if (quizrecord != null) {
                quizrecord = em.getReference(quizrecord.getClass(), quizrecord.getQuizrecordid());
                quizes.setQuizrecord(quizrecord);
            }
            Subjects subjectsSubjectid = quizes.getSubjectsSubjectid();
            if (subjectsSubjectid != null) {
                subjectsSubjectid = em.getReference(subjectsSubjectid.getClass(), subjectsSubjectid.getSubjectid());
                quizes.setSubjectsSubjectid(subjectsSubjectid);
            }
            List<Questions> attachedQuestionsList = new ArrayList<Questions>();
            for (Questions questionsListQuestionsToAttach : quizes.getQuestionsList()) {
                questionsListQuestionsToAttach = em.getReference(questionsListQuestionsToAttach.getClass(), questionsListQuestionsToAttach.getQuestionid());
                attachedQuestionsList.add(questionsListQuestionsToAttach);
            }
            quizes.setQuestionsList(attachedQuestionsList);
            em.persist(quizes);
            if (quizrecord != null) {
                Quizes oldQuizesQuizidOfQuizrecord = quizrecord.getQuizesQuizid();
                if (oldQuizesQuizidOfQuizrecord != null) {
                    oldQuizesQuizidOfQuizrecord.setQuizrecord(null);
                    oldQuizesQuizidOfQuizrecord = em.merge(oldQuizesQuizidOfQuizrecord);
                }
                quizrecord.setQuizesQuizid(quizes);
                quizrecord = em.merge(quizrecord);
            }
            if (subjectsSubjectid != null) {
                subjectsSubjectid.getQuizesList().add(quizes);
                subjectsSubjectid = em.merge(subjectsSubjectid);
            }
            for (Questions questionsListQuestions : quizes.getQuestionsList()) {
                Quizes oldQuizesQuizidOfQuestionsListQuestions = questionsListQuestions.getQuizesQuizid();
                questionsListQuestions.setQuizesQuizid(quizes);
                questionsListQuestions = em.merge(questionsListQuestions);
                if (oldQuizesQuizidOfQuestionsListQuestions != null) {
                    oldQuizesQuizidOfQuestionsListQuestions.getQuestionsList().remove(questionsListQuestions);
                    oldQuizesQuizidOfQuestionsListQuestions = em.merge(oldQuizesQuizidOfQuestionsListQuestions);
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

    public void edit(Quizes quizes) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Quizes persistentQuizes = em.find(Quizes.class, quizes.getQuizid());
            Quizrecord quizrecordOld = persistentQuizes.getQuizrecord();
            Quizrecord quizrecordNew = quizes.getQuizrecord();
            Subjects subjectsSubjectidOld = persistentQuizes.getSubjectsSubjectid();
            Subjects subjectsSubjectidNew = quizes.getSubjectsSubjectid();
            List<Questions> questionsListOld = persistentQuizes.getQuestionsList();
            List<Questions> questionsListNew = quizes.getQuestionsList();
            List<String> illegalOrphanMessages = null;
            if (quizrecordOld != null && !quizrecordOld.equals(quizrecordNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Quizrecord " + quizrecordOld + " since its quizesQuizid field is not nullable.");
            }
            for (Questions questionsListOldQuestions : questionsListOld) {
                if (!questionsListNew.contains(questionsListOldQuestions)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Questions " + questionsListOldQuestions + " since its quizesQuizid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (quizrecordNew != null) {
                quizrecordNew = em.getReference(quizrecordNew.getClass(), quizrecordNew.getQuizrecordid());
                quizes.setQuizrecord(quizrecordNew);
            }
            if (subjectsSubjectidNew != null) {
                subjectsSubjectidNew = em.getReference(subjectsSubjectidNew.getClass(), subjectsSubjectidNew.getSubjectid());
                quizes.setSubjectsSubjectid(subjectsSubjectidNew);
            }
            List<Questions> attachedQuestionsListNew = new ArrayList<Questions>();
            for (Questions questionsListNewQuestionsToAttach : questionsListNew) {
                questionsListNewQuestionsToAttach = em.getReference(questionsListNewQuestionsToAttach.getClass(), questionsListNewQuestionsToAttach.getQuestionid());
                attachedQuestionsListNew.add(questionsListNewQuestionsToAttach);
            }
            questionsListNew = attachedQuestionsListNew;
            quizes.setQuestionsList(questionsListNew);
            quizes = em.merge(quizes);
            if (quizrecordNew != null && !quizrecordNew.equals(quizrecordOld)) {
                Quizes oldQuizesQuizidOfQuizrecord = quizrecordNew.getQuizesQuizid();
                if (oldQuizesQuizidOfQuizrecord != null) {
                    oldQuizesQuizidOfQuizrecord.setQuizrecord(null);
                    oldQuizesQuizidOfQuizrecord = em.merge(oldQuizesQuizidOfQuizrecord);
                }
                quizrecordNew.setQuizesQuizid(quizes);
                quizrecordNew = em.merge(quizrecordNew);
            }
            if (subjectsSubjectidOld != null && !subjectsSubjectidOld.equals(subjectsSubjectidNew)) {
                subjectsSubjectidOld.getQuizesList().remove(quizes);
                subjectsSubjectidOld = em.merge(subjectsSubjectidOld);
            }
            if (subjectsSubjectidNew != null && !subjectsSubjectidNew.equals(subjectsSubjectidOld)) {
                subjectsSubjectidNew.getQuizesList().add(quizes);
                subjectsSubjectidNew = em.merge(subjectsSubjectidNew);
            }
            for (Questions questionsListNewQuestions : questionsListNew) {
                if (!questionsListOld.contains(questionsListNewQuestions)) {
                    Quizes oldQuizesQuizidOfQuestionsListNewQuestions = questionsListNewQuestions.getQuizesQuizid();
                    questionsListNewQuestions.setQuizesQuizid(quizes);
                    questionsListNewQuestions = em.merge(questionsListNewQuestions);
                    if (oldQuizesQuizidOfQuestionsListNewQuestions != null && !oldQuizesQuizidOfQuestionsListNewQuestions.equals(quizes)) {
                        oldQuizesQuizidOfQuestionsListNewQuestions.getQuestionsList().remove(questionsListNewQuestions);
                        oldQuizesQuizidOfQuestionsListNewQuestions = em.merge(oldQuizesQuizidOfQuestionsListNewQuestions);
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
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
            List<String> illegalOrphanMessages = null;
            Quizrecord quizrecordOrphanCheck = quizes.getQuizrecord();
            if (quizrecordOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Quizes (" + quizes + ") cannot be destroyed since the Quizrecord " + quizrecordOrphanCheck + " in its quizrecord field has a non-nullable quizesQuizid field.");
            }
            List<Questions> questionsListOrphanCheck = quizes.getQuestionsList();
            for (Questions questionsListOrphanCheckQuestions : questionsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Quizes (" + quizes + ") cannot be destroyed since the Questions " + questionsListOrphanCheckQuestions + " in its questionsList field has a non-nullable quizesQuizid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
