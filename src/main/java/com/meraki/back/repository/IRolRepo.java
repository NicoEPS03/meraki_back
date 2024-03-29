package com.meraki.back.repository;

import com.meraki.back.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepo extends JpaRepository<Rol,Integer> {
}
