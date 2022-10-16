package org.example.repository;

import org.example.model.School;
import org.example.model.Student;
import org.example.model.Tutor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class StudentRepository {

    private EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;

     public StudentRepository(){
         this.entityManagerFactory = Persistence.createEntityManagerFactory("student_pu");
         this.entityManager = this.entityManagerFactory.createEntityManager();
     }

    public Student addStudent(Student student){
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        return student;
    }

     public Student findStudentById(Long id){
         Query query = entityManager.createNamedQuery("find student by id");
         query.setParameter("id",id);
         return (Student) query.getSingleResult();
     }

    public StudentRepository(String pu){
        this.entityManagerFactory = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }



//     public Student addStudentToSchool(Long id, School school){
//         entityManager.getTransaction().begin();
//         Student student = findStudent(id);
//         student.setSchool(school);
//         entityManager.getTransaction().commit();
//         return student;
//     }

    public Tutor addStudentToTutor(Long id, Tutor tutor){
        entityManager.getTransaction().begin();
         Student student = findStudent(id);
         student.setTutor(tutor);
         entityManager.getTransaction().commit();
         return tutor;
    }

     public Student findStudent(Long id){
         return entityManager.find(Student.class,id);
     }

     public Student updateStudent(Student student){
         Student studentUpdate = entityManager.find(Student.class,student.getId());
         entityManager.getTransaction().begin();
         studentUpdate.setFirstName(student.getFirstName());
         studentUpdate.setLastName(student.getLastName());
         entityManager.getTransaction().commit();
         return student;
     }

     public void delete(Student student){
         entityManager.getTransaction().begin();
         entityManager.remove(student);
         entityManager.getTransaction().commit();
     }

     public List<String> findFirstNames(){
         entityManager.getTransaction().begin();
         Query query = entityManager.createQuery("Select s.firstName from Student s");
         entityManager.getTransaction().commit();
         return query.getResultList();
     }

    public List<String> findLastNames(){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Select s.lastName from Student s");
        entityManager.getTransaction().commit();
        return query.getResultList();
    }

     public void close(){
         entityManager.close();
         entityManagerFactory.close();
     }

}
