package com.dev.GymForDevelopers.repositories;

import com.dev.GymForDevelopers.models.entity.GdNoteHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GdNoteHistoryRepository extends JpaRepository<GdNoteHistory, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true,
            value = "INSERT INTO note_history (section, advice, date_of_creation, who_created, number, likes, dislikes, status)" +
                    "SELECT section, advice, date_of_creation, who_created, number, likes, dislikes, :status FROM note WHERE id = :id ; " +
                    "DELETE FROM note WHERE id = :id")
    void save(@Param("id") Long id, @Param("status") Integer status);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true,
            value = "DELETE FROM note_history WHERE date_of_creation < NOW() - INTERVAL '1 MONTH'")
    void deleteInAMonth();

}
