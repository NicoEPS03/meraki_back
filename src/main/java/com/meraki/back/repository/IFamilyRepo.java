package com.meraki.back.repository;

import com.meraki.back.entity.Athlete;
import com.meraki.back.entity.Family;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFamilyRepo extends JpaRepository<Family, Integer> {
    @Query(value = "SELECT SUM(conteo)\n" +
            " FROM (SELECT COUNT(*) AS conteo FROM public.family f WHERE f.fa_email = :email \n" +
            "UNION ALL SELECT COUNT(*) AS conteo FROM public.athlete a WHERE a.at_email = :email) AS subconsulta", nativeQuery = true)
    public int existsEmailInsert(@Param("email") String email);

    public Family findByDocument(String document);

    @Query(value = "SELECT * FROM public.Family f WHERE f.fa_state = true AND f.fa_idathlete = :athlete", nativeQuery = true)
    public Page<Family> findAllStateTrue(@Param("athlete") int athlete, Pageable pageable);

    public int searchDocument(int id, String document);

    @Query(value = "SELECT * FROM public.Family f WHERE f.fa_state = true AND f.fa_idathlete = :athlete", nativeQuery = true)
    public List<Family> findfamiliars(@Param("athlete") int athlete);
}
