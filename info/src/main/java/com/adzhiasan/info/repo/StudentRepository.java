package com.adzhiasan.info.repo;

import com.adzhiasan.info.models.Student;
import com.adzhiasan.info.models.StudyingGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends CrudRepository<Student, Long> {
//    @Query("SELECT s FROM Students s WHERE s.num = ?1")
//    StudyingGroup findGroupByNum(@Param("groupNum") int groupNum);
}
