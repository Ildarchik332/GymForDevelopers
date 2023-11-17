package com.dev.GymForDevelopers.repositories;

import com.dev.GymForDevelopers.models.entity.GdNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface GdNoteRepository extends JpaRepository<GdNote, Integer> {

    Optional<GdNote> findById(Integer id);

    GdNote findNoteById(Integer id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true,
            value = "INSERT INTO note (section, advice, date_of_creation, who_created, status)" +
                    " SELECT section, advice, date_of_creation, who_created, :status FROM note_history WHERE id = :id ; " +
                    "DELETE FROM note_history WHERE id = :id")
    void save(@Param("id") Integer id, @Param("status") Integer status);


    List <GdNote> findByStatusEquals(Integer status);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "UPDATE note SET status = :status WHERE id = :id")
    int saveNewStatus(@Param("id")Integer id, @Param("status") Integer status);
}
