package com.example.Airline.reservation.system.controller;
import com.example.Airline.reservation.system.entity.Flight;
import com.example.Airline.reservation.system.entity.Reservation;
import com.example.Airline.reservation.system.entity.User;
import com.example.Airline.reservation.system.service.FlightService;
import com.example.Airline.reservation.system.service.ReservationService;
import com.example.Airline.reservation.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;



@Controller

public class ReservationController {
    static Flight flight;

    @Autowired
    UserService userService;

    @Autowired
    FlightService flightService;
    @Autowired
    ReservationService reservationService;

    @GetMapping("/reservation/{id}")
    public String showCompleteReservation(@PathVariable("id") Integer id, Model model) {
        flight = flightService.findById(id);
        model.addAttribute("flight", flight);
        return "completeReservation";
    }


    @PostMapping("/reservation/complete-reservation")
    public String completeReservation(HttpServletRequest request, Model model) {
        String email = request.getParameter("pEmail");
        User user = userService.getUserByEmail(email);

        Reservation reservation = new Reservation(flight,user);
        reservationService.create(reservation);
        model.addAttribute("msg", "Reservation completed successfully");
        model.addAttribute("pEmail", user.getEmail());
        model.addAttribute("id", flight.getId());
        model.addAttribute("firstname", user.getFirstName());
        model.addAttribute("lastname", user.getLastName());
        model.addAttribute("pPhone", request.getParameter("pPhone"));
        model.addAttribute("flightnumber", flight.getFlightNumber());
        model.addAttribute("arrivalcity", flight.getArrivalCity());
        model.addAttribute("dateofdeparture", flight.getDateOfDeparture());
        model.addAttribute("departurecity", flight.getDepartureCity());
        model.addAttribute("estimatedDepartureTime", flight.getEstimatedDepartureTime());
        model.addAttribute("operatingAirlines", flight.getOperatingAirlines());

        return "reservationConfirmation";
    }
}




