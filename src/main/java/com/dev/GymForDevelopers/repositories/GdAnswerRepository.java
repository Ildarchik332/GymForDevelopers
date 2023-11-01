package com.dev.GymForDevelopers.repositories;

import com.dev.GymForDevelopers.models.entity.GdAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GdAnswerRepository extends JpaRepository<GdAnswer, Integer> {
}
