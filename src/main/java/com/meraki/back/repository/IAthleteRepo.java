package com.meraki.back.repository;

import com.meraki.back.entity.Athlete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAthleteRepo extends JpaRepository<Athlete, Integer> {
    public int searchDocument(int id, String document);
    @Query(value = "SELECT COUNT(*) FROM public.Athlete a WHERE a.at_idclub = :club AND a.at_state = true", nativeQuery = true)
    public int searchAthlete(@Param("club")int club);

    public Athlete findByDocumentAndState(String document, boolean state);

    @Query(value = "SELECT * FROM public.Athlete a WHERE a.at_idclub = :club AND a.at_state = true", nativeQuery = true)
    public Page<Athlete> findAllStateTrue(@Param("club") int club, Pageable pageable);

    @Query(value = "SELECT * FROM public.Athlete a WHERE a.at_idclub = :club ", nativeQuery = true)
    public List<Athlete> findAllClub(@Param("club") int club);
}
