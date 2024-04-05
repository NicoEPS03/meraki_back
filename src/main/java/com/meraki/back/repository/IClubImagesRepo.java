package com.meraki.back.repository;

import com.meraki.back.entity.ClubImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IClubImagesRepo extends JpaRepository<ClubImages, Integer> {
    @Query(value = "SELECT COUNT(*) FROM club_images ci WHERE ci.ci_idclub = :club AND ci.ci_state = true AND ci.ci_other = true", nativeQuery = true)
    public int numberOtherImages(@Param("club") int club);

    @Query(value = "SELECT COUNT(*) FROM club_images ci WHERE ci.ci_idclub = :club AND ci.ci_state = true AND ci.ci_banner = true", nativeQuery = true)
    public int numberBannerImages(@Param("club") int club);

    @Query(value = "SELECT COUNT(*) FROM club_images ci WHERE ci.ci_idclub = :club AND ci.ci_state = true AND ci.ci_logo = true", nativeQuery = true)
    public int numberLogoImages(@Param("club") int club);

    @Query(value = "SELECT * FROM club_images ci WHERE ci.ci_idclub = :club AND ci.ci_state = true", nativeQuery = true)
    public List<ClubImages> clubImages(@Param("club") int club);
}

