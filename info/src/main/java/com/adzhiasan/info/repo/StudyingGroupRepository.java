package com.adzhiasan.info.repo;

import com.adzhiasan.info.models.StudyingGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface StudyingGroupRepository extends CrudRepository<StudyingGroup, Long> {
    @Query("SELECT s FROM StudyingGroup s WHERE s.num = ?1")
    StudyingGroup findGroupByNum(@Param("groupNum") int groupNum);
}
