package me.nobler.spring.ticketbooking.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class FlightAccOrder implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer flightId;

    private String payBank;

    private String uid;

    private String phoneNum;

    public FlightAccOrder(Integer flightId, String payBank, String uid, String phoneNum) {
        this.flightId = flightId;
        this.payBank = payBank;
        this.uid = uid;
        this.phoneNum = phoneNum;
    }

    public FlightAccOrder() {
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
