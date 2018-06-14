package me.nobler.spring.air_server.Repository;

import me.nobler.spring.air_server.Entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;


public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findFlightsByDayAndDepartureAndDestination(Date day, String departure, String destination);

}
