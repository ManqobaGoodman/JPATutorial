package org.example;

import javafx.scene.input.KeyCode;
import org.example.model.School;
import org.example.model.Student;
import org.example.model.Teacher;
import org.example.model.Tutor;
import org.example.repository.SchoolRepository;
import org.example.repository.StudentRepository;
import org.example.repository.TeacherRepository;
import org.example.repository.TutorRepository;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Student student = new Student();
        student.setFirstName("Pearl");
        student.setLastName("Lubisi");

        StudentRepository studentRepository = new StudentRepository();
        SchoolRepository schoolRepository = new SchoolRepository();
        TutorRepository tutorRepository = new TutorRepository();
        TeacherRepository teacherRepository = new TeacherRepository();

        studentRepository.addStudent(student);

        System.out.println("Added Student: "+ student.toString());

        Tutor tutor = new Tutor("firstNameTutor","lastNameTutor");

        tutorRepository.addTutor(tutor);

        System.out.println("Added tutor: "+ tutor.toString());

        studentRepository.addStudentToTutor(student.getId(),tutor);
        System.out.println("Added Student To Tutor: "+ student.toString());

        School school = new School("School_1","City_1");

        schoolRepository.addSchool(school);
        System.out.println("Added school: "+ school.toString());

        schoolRepository.addStudent(school.getId(),student);
        System.out.println("Added Student To School: "+ school.toString());

        school = schoolRepository.findSchool(school.getId());

        //school.getStudents().forEach(System.out::println);

        Teacher teacher = new Teacher("Marry","Marry");
        //Teacher teacher1 = new Teacher("Tom","Boss");

       // teacher1.setSchool(school);
        //teacher.setSchool(school);

        teacher.addStudent(new Student("Manqoba","Goodman"));
        teacher.addStudent(new Student("Bosszonke","Goodman"));

        teacherRepository.addTeacher(teacher);
        System.out.println("Added teacher: "+ teacher.toString());

        //teacherRepository.addTeacher(teacher1);
        //System.out.println("Added teacher1: "+ teacher1.toString());

        studentRepository.findFirstNames().forEach(System.out::println);
        studentRepository.findLastNames().forEach(System.out::println);

        Student student1 = studentRepository.findStudent(student.getId());

        System.out.println("Found Student: "+ student1.toString());

        student.setLastName("EMS");

        studentRepository.updateStudent(student);

        System.out.println("Updated Student: "+ student);

        //studentRepository.delete(student);

        studentRepository.close();
    }
}
