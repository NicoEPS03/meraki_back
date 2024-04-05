package com.meraki.back.repository;

import com.meraki.back.entity.Athlete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAthleteRepo extends JpaRepository<Athlete, Integer> {
    public int searchDocument(int id, String document);
    @Query(value = "SELECT COUNT(*) FROM Athlete a WHERE a.at_idclub = :club AND a.at_state = true", nativeQuery = true)
    public int searchAthlete(@Param("club")int club);

    public Athlete findByDocument(String document);

    @Query(value = "SELECT * FROM Athlete a WHERE a.at_idclub = :club AND a.at_state = true", nativeQuery = true)
    public Page<Athlete> findAllStateTrue(@Param("club") int club, Pageable pageable);
}
