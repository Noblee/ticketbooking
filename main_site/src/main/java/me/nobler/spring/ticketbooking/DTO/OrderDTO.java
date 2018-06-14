package me.nobler.spring.ticketbooking.DTO;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class OrderDTO implements Serializable {
    private Integer orderId;
    private String flightProvider;
    private Integer flightOid;
    private Integer id;
    private Date day;
    private String departure;
    private String destination;
    private Time go_off;
    private Time arrival;
    private Integer prize;
    private String isPaid;

    public OrderDTO(Integer orderId, String flightProvider, Integer flightOid, Integer id, Date day, String departure, String destination, Time go_off, Time arrival, Integer prize, String isPaid) {
        this.orderId = orderId;
        this.flightProvider = flightProvider;
        this.flightOid = flightOid;
        this.id = id;
        this.day = day;
        this.departure = departure;
        this.destination = destination;
        this.go_off = go_off;
        this.arrival = arrival;
        this.prize = prize;
        this.isPaid = isPaid;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getFlightProvider() {
        return flightProvider;
    }

    public void setFlightProvider(String flightProvider) {
        this.flightProvider = flightProvider;
    }

    public Integer getFlightOid() {
        return flightOid;
    }

    public void setFlightOid(Integer flightOid) {
        this.flightOid = flightOid;
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

    public Time getGo_off() {
        return go_off;
    }

    public void setGo_off(Time go_off) {
        this.go_off = go_off;
    }

    public Time getArrival() {
        return arrival;
    }

    public void setArrival(Time arrival) {
        this.arrival = arrival;
    }

    public Integer getPrize() {
        return prize;
    }

    public void setPrize(Integer prize) {
        this.prize = prize;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }
}
