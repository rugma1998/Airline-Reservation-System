package com.example.Airline.reservation.system.controller;

import com.example.Airline.reservation.system.entity.Reservation;
import com.example.Airline.reservation.system.entity.User;

import com.example.Airline.reservation.system.service.ReservationService;
import com.example.Airline.reservation.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Objects;

@Controller
public class UserController {

    static User activeUser;

    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;


    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/registerUser")
    public String showRegistration(){
        return "registerUser";
    }

    @RequestMapping("/loginUser")
    public String showLogin(){
        return "login";
    }

    @RequestMapping("register-user")
    public String register(@ModelAttribute User user){
        userService.create(user);
        return "login";
    }

    @PostMapping("login-user")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model){
        User user =  userService.getUserByEmailAndPassword(email,password);
        if (!(Objects.isNull(user))) {
            activeUser = user;
            return "findFlights";
        }
        else{
            model.addAttribute("msg","Invalid credentials");
        }
        return "login";
    }

    @GetMapping("/flights/find")
    public  String findFlights(){
        return "findFlights";
    }




    @GetMapping("/viewReservations")
    public String viewReservations(Principal principal, Model model){
        String username = principal.getName();
        User user = userService.getUserByEmail(username);
        Integer id = user.getId();
        model.addAttribute("reservations",reservationService.myReservationList(id));
        return "viewReservations";
    }


    @GetMapping("/updateUser")
    public String updateUserDetails(Principal principal,Model model){
        String username = principal.getName();
        User user = userService.getUserByEmail(username);
        Integer id = user.getId();
        model.addAttribute("user",userService.findById(id));
        return "updateUser";
    }

    @PostMapping("/profile")
    public String profile(HttpServletRequest request, Model model){
        User user=userService.getUserByEmail(request.getParameter("email"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setPassword(request.getParameter("password"));
        userService.updateUser(user);
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String showProfile(Principal principal, Model model){
        String username = principal.getName();
        User user = userService.getUserByEmail(username);
        model.addAttribute("user",user);
        return "profile";
    }
}

