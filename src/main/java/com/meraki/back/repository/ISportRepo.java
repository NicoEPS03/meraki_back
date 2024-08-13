package com.meraki.back.repository;

import com.meraki.back.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISportRepo extends JpaRepository<Sport, Integer> {
    @Query("SELECT s FROM Sport s ORDER BY s.name ASC")
    List<Sport> findAllSportOrderAsc();
}
