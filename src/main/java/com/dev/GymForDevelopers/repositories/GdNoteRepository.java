package com.dev.GymForDevelopers.repositories;

import com.dev.GymForDevelopers.models.entity.GdNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GdNoteRepository extends JpaRepository<GdNote, Integer> {
}
