package org.example.repository;


import org.example.model.Teacher;
import org.example.model.Tutor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TeacherRepository {

    private EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;

    public TeacherRepository(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("student_pu");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    public TeacherRepository(String pu){
        this.entityManagerFactory = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }


    public Teacher addTeacher(Teacher teacher){
        entityManager.getTransaction().begin();
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
        return teacher;
    }

    public Teacher findTeacher(Long id){
        return entityManager.find(Teacher.class,id);
    }

    public Teacher updateTeacher(Teacher teacher){
        Teacher teacherUpdate = entityManager.find(Teacher.class,teacher.getId());
        entityManager.getTransaction().begin();
        teacherUpdate.setFirstName(teacher.getFirstName());
        teacherUpdate.setLastName(teacher.getLastName());
        entityManager.getTransaction().commit();
        return teacherUpdate;
    }

    public void deleteTeacher(Teacher teacher){
        entityManager.getTransaction().begin();
        entityManager.remove(teacher);
        entityManager.getTransaction().commit();
    }

    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
