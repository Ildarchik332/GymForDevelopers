package com.dev.GymForDevelopers.repositories;

import com.dev.GymForDevelopers.models.entity.GdPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GdPersonRepository extends JpaRepository<GdPerson, Long> {


    @Transactional(readOnly = true)
    @Query("select p.extraInfo from GdPerson p where p.id = :id")
    GdPerson.ExtraInfo getExtraInfoById(@Param("id") Long id);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "UPDATE person SET extra_info = :extraInfo WHERE id = :id")
    void saveExtraInfo(@Param("id") Long id, @Param("extraInfo") String extraInfo);
}
