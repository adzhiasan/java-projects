package com.adzhiasan.info.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;
    private String surname, name, fatherName;
//    private LocalDateTime birthDate;
    private String gender;
    private int entryYear;

    @ManyToOne
    private StudyingGroup studyingGroup;


    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

//    public LocalDateTime getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(LocalDateTime birthDate) {
//        this.birthDate = birthDate;
//    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getEntryYear() {
        return entryYear;
    }

    public void setEntryYear(int entryYear) {
        this.entryYear = entryYear;
    }

    public StudyingGroup getStudyingGroup() {
        return studyingGroup;
    }

    public void setStudyingGroup(StudyingGroup studyingGroup) {
        this.studyingGroup = studyingGroup;
    }

    public Student() {
    }

    public Student(String surname, String name, String fatherName, String gender, int entryYear, StudyingGroup studyingGroup) {
        this.surname = surname;
        this.name = name;
        this.fatherName = fatherName;
//        this.birthDate = birthDate;
        this.gender = gender;
        this.entryYear = entryYear;
        this.studyingGroup = studyingGroup;
    }
}