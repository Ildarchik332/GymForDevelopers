package com.dev.GymForDevelopers.repositories;

import com.dev.GymForDevelopers.models.entity.GdComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GdCommentRepository extends JpaRepository<GdComment, Long> {

}
