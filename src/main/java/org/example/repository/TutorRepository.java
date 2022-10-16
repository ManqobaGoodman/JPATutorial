package org.example.repository;


import org.example.model.Tutor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TutorRepository {

    private EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;

    public TutorRepository(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("student_pu");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    public TutorRepository(String pu){
        this.entityManagerFactory = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }


    public Tutor addTutor(Tutor tutor){
        entityManager.getTransaction().begin();
        entityManager.persist(tutor);
        entityManager.getTransaction().commit();
        return tutor;
    }

    public Tutor findTutor(Long id){
        return entityManager.find(Tutor.class,id);
    }

    public Tutor updateTutor(Tutor tutor){
        Tutor tutorUpdate = entityManager.find(Tutor.class,tutor.getId());
        entityManager.getTransaction().begin();
        tutorUpdate.setFirstName(tutor.getFirstName());
        tutorUpdate.setLastName(tutor.getLastName());
        entityManager.getTransaction().commit();
        return tutorUpdate;
    }

    public void deleteTutor(Tutor tutor){
        entityManager.getTransaction().begin();
        entityManager.remove(tutor);
        entityManager.getTransaction().commit();
    }

    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
