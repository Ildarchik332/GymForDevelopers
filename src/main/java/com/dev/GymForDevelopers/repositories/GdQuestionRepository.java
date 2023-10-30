package com.dev.GymForDevelopers.repositories;

import com.dev.GymForDevelopers.models.entity.GdQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GdQuestionRepository extends JpaRepository<GdQuestion, Integer> {

}
