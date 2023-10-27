package com.dev.GymForDevelopers.repositories;

import com.dev.GymForDevelopers.models.entity.GdQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GdQuestionRepository extends JpaRepository<GdQuestion, Integer> {

}
