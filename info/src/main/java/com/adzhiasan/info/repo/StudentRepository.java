package com.adzhiasan.info.repo;

import com.adzhiasan.info.models.Student;
import com.adzhiasan.info.models.StudyingGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.studyingGroup = ?1")
    Iterable<Student> findByGroup(@Param("sGroup") StudyingGroup sGroup);
}
