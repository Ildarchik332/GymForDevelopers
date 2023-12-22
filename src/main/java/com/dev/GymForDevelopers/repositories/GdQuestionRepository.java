package com.dev.GymForDevelopers.repositories;

import com.dev.GymForDevelopers.models.entity.GdQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GdQuestionRepository extends JpaRepository<GdQuestion, Integer> {

    GdQuestion findById(Long questionId);

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT issue FROM question ORDER BY random() LIMIT :count")
    List<String> getRandomQuestion(@Param("count") Integer count);

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT issue FROM question WHERE section =:section ORDER BY random() LIMIT :count")
    List<String> getRandomFromSection(@Param("section") String section, @Param("count") Integer count);
}
