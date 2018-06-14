package me.nobler.spring.air_server.Repository;

import me.nobler.spring.air_server.Entity.FlightOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<FlightOrder, Integer> {

}
