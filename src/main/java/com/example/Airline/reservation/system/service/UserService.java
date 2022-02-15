package com.example.Airline.reservation.system.service;

import com.example.Airline.reservation.system.entity.User;
import com.example.Airline.reservation.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User getUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);

    }

    public void create(User user) {
        userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void updateUser(User activeUser) {
        userRepository.save(activeUser);
    }

    public User findById(Integer id) {
        return userRepository.getById(id);

    }
}
