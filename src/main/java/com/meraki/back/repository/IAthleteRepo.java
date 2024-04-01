package com.meraki.back.repository;

import com.meraki.back.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAthleteRepo extends JpaRepository<Athlete, Integer> {
}
