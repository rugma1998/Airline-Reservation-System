package com.example.Airline.reservation.system.service;
import com.example.Airline.reservation.system.entity.Reservation;
import com.example.Airline.reservation.system.repository.FlightRepository;
import com.example.Airline.reservation.system.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    ReservationRepository reservationRepository;

    public void create(Reservation reservation) {

        reservationRepository.save(reservation);
    }

    public List<Reservation> myReservationList(Integer id) {
        return reservationRepository.findByUserId(id);

    }
}

