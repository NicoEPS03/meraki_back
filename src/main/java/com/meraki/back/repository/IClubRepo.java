package com.meraki.back.repository;

import com.meraki.back.entity.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IClubRepo extends JpaRepository<Club, Integer> {
    public Page<Club> findAllByState(boolean state, Pageable pageable);
    @Query(value = "SELECT * FROM public.Club c WHERE c.cb_idsport = :sport AND c.cb_state = true", nativeQuery = true)
    public Page<Club> searchBySport(@Param("sport") int sport, Pageable pageable);

    @Query(value = "SELECT * FROM public.Club c WHERE c.cb_idcity = :city AND c.cb_state = true", nativeQuery = true)
    public Page<Club> searchByCity(@Param("city") int city, Pageable pageable);

    @Query(value = "SELECT * FROM public.Club c WHERE c.cb_idcity = :city AND c.cb_idsport = :sport AND c.cb_state = true", nativeQuery = true)
    public Page<Club> searchBySportAndCity(@Param("sport") int sport, @Param("city") int city, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM public.Club c WHERE c.cb_idcity = :city AND c.cb_idsport = :sport AND c.cb_state = true", nativeQuery = true)
    public Integer numSportAndCity(@Param("sport") int sport, @Param("city") int city);
}
