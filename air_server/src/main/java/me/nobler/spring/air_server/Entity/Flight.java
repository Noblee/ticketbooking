package me.nobler.spring.air_server.Entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Flight implements Serializable {
    @Id
    private Integer id;
    private Date day;
    private String departure;
    private String destination;
    private Time arrival;
    private Time go_off;
    private Integer prize;
    private Integer remaining;

    public Flight() {
    }

    public Flight(Integer id, Date day, String departure, String destination, Time arrival, Time go_off, Integer prize, Integer remaining) {

        this.id = id;
        this.day = day;
        this.departure = departure;
        this.destination = destination;
        this.arrival = arrival;
        this.go_off = go_off;
        this.prize = prize;
        this.remaining = remaining;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Time getArrival() {
        return arrival;
    }

    public void setArrival(Time arrival) {
        this.arrival = arrival;
    }

    public Time getGo_off() {
        return go_off;
    }

    public void setGo_off(Time go_off) {
        this.go_off = go_off;
    }

    public Integer getPrize() {
        return prize;
    }

    public void setPrize(Integer prize) {
        this.prize = prize;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }
}
