package com.BridgeLabz.BookstoreApp.repository;

import com.BridgeLabz.BookstoreApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT * FROM users where email=:email_Id", nativeQuery = true)
    public Optional<User> findByEmailid(String email_Id);

}
