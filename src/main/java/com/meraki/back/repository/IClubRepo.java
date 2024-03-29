package com.meraki.back.repository;

import com.meraki.back.entity.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClubRepo extends JpaRepository<Club,Integer> {
    /*public Page<Club> findBySport(int sportId);
    public Page<Club> findByCity(int cityId);
    public Page<Club> findBySportAndCity(int sportId, int cityId);*/
}
