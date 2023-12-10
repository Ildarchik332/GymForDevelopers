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
public interface GdNoteRepository extends JpaRepository<GdNote, Long> {

    Optional<GdNote> findById(Long id);

    GdNote findNoteById(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true,
            value = "INSERT INTO note (section, advice, date_of_creation, who_created, number, likes, dislikes, status)" +
                    "SELECT section, advice, date_of_creation, who_created, number, likes, dislikes, :status FROM note_history WHERE id = :id ; " +
                    "DELETE FROM note_history WHERE id = :id")
    void save(@Param("id") Long id, @Param("status") Integer status);


    List<GdNote> findByStatusEquals(Integer status);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "UPDATE note SET status = :status WHERE id = :id")
    int saveNewStatus(@Param("id") Long id, @Param("status") Integer status);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "UPDATE note SET likes = :likes WHERE id = :id")
    int saveNoteLikes(@Param("id") Long id, @Param("likes") Long likes);

    @Query(nativeQuery = true, value = "SELECT id, likes FROM note")
    List<Long[]> loadNoteLikes();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "UPDATE note SET dislikes = :dislikes WHERE id = :id")
    int saveNoteDislikes(@Param("id") Long id, @Param("dislikes")Long dislikes);

    @Query(nativeQuery = true, value = "SELECT id, dislikes FROM note")
    List<Long[]> loadNoteDislikes();


}
