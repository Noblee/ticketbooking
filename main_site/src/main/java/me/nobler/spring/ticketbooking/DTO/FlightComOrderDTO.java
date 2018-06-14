package me.nobler.spring.ticketbooking.DTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
public class FlightComOrderDTO implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer flightId;

    private String payBank;

    private String uid;

    private Date day;

    private String departure;

    private String destination;

    private Time arrival;

    private Time go_off;

    private Integer prize;

    public FlightComOrderDTO() {
    }

    public FlightComOrderDTO(Integer flightId, String payBank, String uid, Date day, String departure, String destination, Time arrival, Time go_off, Integer prize) {

        this.flightId = flightId;
        this.payBank = payBank;
        this.uid = uid;
        this.day = day;
        this.departure = departure;
        this.destination = destination;
        this.arrival = arrival;
        this.go_off = go_off;
        this.prize = prize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getPayBank() {
        return payBank;
    }

    public void setPayBank(String payBank) {
        this.payBank = payBank;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
}
