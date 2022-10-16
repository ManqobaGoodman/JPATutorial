package org.example.repository;

import org.example.model.School;
import org.example.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class SchoolRepository {

    private EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;

    public SchoolRepository(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("student_pu");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    public SchoolRepository(String pu){
        this.entityManagerFactory = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }


    public School addSchool(School school){
        entityManager.getTransaction().begin();
        entityManager.persist(school);
        entityManager.getTransaction().commit();
        return school;
    }

    public School findSchool(Long id){
        return entityManager.find(School.class,id);
    }

    public School updateSchool(School school){
        School schoolUpdate = entityManager.find(School.class,school.getId());
        entityManager.getTransaction().begin();
        schoolUpdate.setSchoolName(school.getSchoolName());
        schoolUpdate.setCity(school.getCity());
        entityManager.getTransaction().commit();
        return schoolUpdate;
    }

    public void deleteSchool(School school){
        entityManager.getTransaction().begin();
        entityManager.remove(school);
        entityManager.getTransaction().commit();
    }

    public void addStudent(Long id, Student student){
        entityManager.getTransaction().begin();
        School school = findSchool(id);
        if (school != null){
            school.getStudents().add(student);
            entityManager.persist(school);
        }
        entityManager.getTransaction().commit();
    }
    public void deleteStudent(Long id, Student student){
        entityManager.getTransaction().begin();
        School school = findSchool(id);
        if (school != null){
            school.getStudents().remove(student);
        }
        entityManager.getTransaction().commit();
    }



    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
