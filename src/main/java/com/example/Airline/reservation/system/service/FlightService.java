package com.example.Airline.reservation.system.service;

import com.example.Airline.reservation.system.entity.Flight;
import com.example.Airline.reservation.system.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;


    public Flight findById(Integer id) {
        return flightRepository.getById(id);
    }
}
