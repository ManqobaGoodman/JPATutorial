package org.example.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "school_name", nullable = false, length = 255)
    private String schoolName;

    @Column(name = "city", nullable = false, length = 255)
    private String city;

    @OneToMany(targetEntity = Student.class)
    private Set<Student> students = new HashSet<>();

    public School() {
    }

    public School(String schoolName, String city) {
        this.schoolName = schoolName;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", schoolName='" + schoolName + '\'' +
                ", city='" + city + '\'' +
                ", students=" + students +
                '}';
    }
}
