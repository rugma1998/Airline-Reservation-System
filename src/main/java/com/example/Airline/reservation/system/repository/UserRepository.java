package com.example.Airline.reservation.system.repository;

import com.example.Airline.reservation.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);

    @Query("Select user from User user where user.email=?1 and user.password=?2")
    User findByEmailAndPassword(String email, String password);
}
