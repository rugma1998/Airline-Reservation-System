package com.example.Airline.reservation.system.entity;



import javax.persistence.*;

@Entity
public class Reservation{
    @Id
    @GeneratedValue
    private Integer id;


    @OneToOne
    private Flight flight;
    @ManyToOne
    @JoinColumn
    private User user;
    public Reservation() {
    }
    public Reservation(Flight flight, User user) {
        this.flight = flight;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
