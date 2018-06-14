package me.nobler.spring.ticketbooking.Repository;

import me.nobler.spring.ticketbooking.Entity.FlightOrder;
import me.nobler.spring.ticketbooking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FlightOrderRepository extends JpaRepository<FlightOrder, Integer> {
    List<FlightOrder> findByUsername(String username);
}
