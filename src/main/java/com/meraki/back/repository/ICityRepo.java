package com.meraki.back.repository;

import com.meraki.back.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICityRepo extends JpaRepository<City, Integer> {
}
