package com.meraki.back.repository;

import com.meraki.back.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
    public User findByDocument(String document);
    public User findByDocumentAndState(String document, boolean state);
    @Query(value = "SELECT * FROM User u WHERE u.usr_state = true", nativeQuery = true)
    public Page<User> findAllStateTrue(Pageable pageable);

    @Query(value = "SELECT * FROM User u WHERE u.usr_document = :document AND NOT u.usr_id = :id", nativeQuery = true)
    public User findByDocumentAlready(@Param("document") String document, @Param("id") int id);

    public int searchDocument(int id, String document);
    @Query(value = "SELECT * FROM User u WHERE u.usr_document = :document AND u.usr_password = :password AND u.usr_state = true", nativeQuery = true)
    public User login(@Param("document") String document, @Param("password") String password);

}
