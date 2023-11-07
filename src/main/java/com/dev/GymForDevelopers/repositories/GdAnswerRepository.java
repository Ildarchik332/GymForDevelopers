package com.dev.GymForDevelopers.repositories;

import com.dev.GymForDevelopers.models.entity.GdAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface GdAnswerRepository extends JpaRepository<GdAnswer, Integer> {
}
