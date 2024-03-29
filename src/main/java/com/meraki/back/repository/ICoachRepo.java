package com.meraki.back.repository;

import com.meraki.back.entity.Coach;
import com.meraki.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICoachRepo extends JpaRepository<Coach,Integer> {
    //public Coach findByDocument(String document);
}
