package me.nobler.spring.ticketbooking.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class FlightOrder implements Serializable {
    @Id
    @GeneratedValue
    private  Integer id;
    private  String username;
    private  String flightCom;
    private  Integer flightOid;
    public FlightOrder(String username, String flightCom, Integer flightOid) {
        this.username = username;
        this.flightCom = flightCom;
        this.flightOid = flightOid;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public FlightOrder() {

    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlightCom() {
        return flightCom;
    }

    public void setFlightCom(String flightCom) {
        this.flightCom = flightCom;
    }

    public Integer getFlightOid() {
        return flightOid;
    }

    public void setFlightOid(Integer flightOid) {
        this.flightOid = flightOid;
    }
}
