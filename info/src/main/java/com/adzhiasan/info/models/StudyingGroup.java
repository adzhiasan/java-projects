package com.adzhiasan.info.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class StudyingGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int num;
    private String curator;
    private String fieldOfStudy;
    @OneToMany(mappedBy = "studyingGroup")
    private List<Student> students;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCurator() {
        return curator;
    }

    public void setCurator(String curator) {
        this.curator = curator;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public StudyingGroup() {
    }

    public StudyingGroup(int num, String curator, String fieldOfStudy) {
        this.num = num;
        this.curator = curator;
        this.fieldOfStudy = fieldOfStudy;
    }
}
