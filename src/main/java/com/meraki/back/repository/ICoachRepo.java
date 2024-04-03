package com.meraki.back.repository;

import com.meraki.back.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICoachRepo extends JpaRepository<Coach, Integer> {
    @Query(value = "SELECT COUNT(*) FROM Coach c WHERE c.ch_idclub = :club", nativeQuery = true)
    public int coachExits(@Param("club") int club);
    @Query(value = "SELECT COUNT(*) FROM Coach c WHERE c.ch_idclub = :club AND NOT c.ch_id = :id", nativeQuery = true)
    public int coachAlreadyExits(@Param("club") int club, @Param("id") int id);

    @Query(value = "SELECT c.* FROM Coach c JOIN User u ON u.usr_id = c.ch_iduser WHERE c.ch_idclub = :club AND u.usr_state = true", nativeQuery = true)
    public Coach getCoach(@Param("club") int club);
}
