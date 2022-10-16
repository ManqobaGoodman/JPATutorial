package org.example;

import org.example.model.Student;
import org.example.repository.StudentRepository;
import org.junit.*;

import static org.junit.Assert.*;

public class StudentRepositoryTest {

    private static StudentRepository studentRepository;

    @BeforeClass
    public static void beforeClass() throws Exception {
        studentRepository = new StudentRepository("student_pu_test");
    }

    @AfterClass
    public static void afterClass() throws Exception {
        studentRepository.close();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addStudent() {
        Student student = new Student();
        student.setFirstName("Manqoba");
        student.setLastName("Lubisi");

        studentRepository.addStudent(student);

        assertNotNull(student.getId());
        assertTrue(student.getId() != null);
    }

    @Test
    public void findStudent() {

        Student student = new Student();
        student.setFirstName("Manqoba");
        student.setLastName("Lubisi");

        studentRepository.addStudent(student);

        Student studentFound = studentRepository.findStudent(student.getId());

        assertNotNull(student);
        assertNotNull(student.getId());
        assertEquals("Lubisi",student.getLastName());
    }

    @Test
    public void updateStudent() {
        Student student = new Student();
        student.setFirstName("Manqoba");
        student.setLastName("Lubisi");

        student = studentRepository.addStudent(student);

        student.setLastName("Goodman");
        studentRepository.updateStudent(student);

        assertNotNull(student);
        assertEquals("Goodman",student.getLastName());

    }

    @Test
    public void delete() {

        Student student = new Student();
        student.setFirstName("Manqoba");
        student.setLastName("Lubisi");

        studentRepository.addStudent(student);

        studentRepository.delete(student);

        student = studentRepository.findStudent(student.getId());

        assertNull(student);
    }
}