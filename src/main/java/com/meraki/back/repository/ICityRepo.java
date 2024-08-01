package com.meraki.back.repository;

import com.meraki.back.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICityRepo extends JpaRepository<City, Integer> {
    @Query(value = "SELECT c.* FROM public.city c JOIN public.club cl ON c.ct_id = cl.cb_idcity JOIN public.sport s ON s.sp_id = cl.cb_idsport " +
            "WHERE s.sp_id = :sportId AND cl.cb_state = true GROUP BY c.ct_id", nativeQuery = true)
    public List<City> listsCitys(@Param("sportId") Integer sport);
}
