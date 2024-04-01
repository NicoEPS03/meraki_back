package com.meraki.back.repository;

import com.meraki.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
    public User findByDocument(String document);

    public int searchDocument(int id, String document);
}
