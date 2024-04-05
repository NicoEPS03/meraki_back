package com.meraki.back.repository;

import com.meraki.back.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISportRepo extends JpaRepository<Sport, Integer> {
}
