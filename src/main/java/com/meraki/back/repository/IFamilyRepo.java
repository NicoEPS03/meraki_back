package com.meraki.back.repository;

import com.meraki.back.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFamilyRepo extends JpaRepository<Family,Integer> {
}
