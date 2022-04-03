package com.springboot.application.myapplication.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="roll_number")
    private int rollNo;

    @Column(name="teacher_id")
    private int teacherId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="standard")
    private String standard;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="teacher_id",insertable=false,updatable = false)
    private Teacher teacher;

    public Student(int rollNo, int teacherId, String firstName, String lastName, String standard, Teacher teacher) {
        this.rollNo = rollNo;
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.standard = standard;
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo=" + rollNo +
                ", teacherId=" + teacherId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", standard='" + standard + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
