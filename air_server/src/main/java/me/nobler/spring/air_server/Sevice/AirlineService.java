package me.nobler.spring.air_server.Sevice;//package me.nobler.spring.bank_server.Sevice;

import me.nobler.spring.air_server.DTO.FlightOrderDTO;
import me.nobler.spring.air_server.Entity.Flight;
import me.nobler.spring.air_server.Entity.FlightOrder;
import me.nobler.spring.air_server.Repository.FlightRepository;
import me.nobler.spring.air_server.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;


@Service
public class AirlineService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(rollbackFor = Exception.class)
    public List<Flight> getFlights(Date date, String departure, String destination) {
        return flightRepository.findFlightsByDayAndDepartureAndDestination(date, departure, destination);
    }
    @Transactional(rollbackFor = Exception.class)
    public FlightOrderDTO getOrderById(Integer id) {
        FlightOrder flightOrder = orderRepository.findById(id).get();
        Flight flight =flightRepository.findById(flightOrder.getFlightId()).get();
        FlightOrderDTO flightOrderDTO = new FlightOrderDTO(id,flightOrder.getFlightId(),flightOrder.getPayBank() ,
                flightOrder.getUid() , flight.getDay(),
                flight.getDeparture(),flight.getDestination() , flight.getArrival(),flight.getGo_off() , flight
                .getPrize());
        return flightOrderDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public Flight getFlightById(Integer id) {
        return flightRepository.findById(id).get();
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer addOrder(Integer flightId,String uid,String phoneNum){
        Flight flight = flightRepository.findById(flightId).get();
        if(flight.getRemaining()<=0) {
            return -1;
        }
        flight.setRemaining(flight.getRemaining()-1);
        flightRepository.save(flight);
        FlightOrder flightOrder = new FlightOrder(flightId,null ,uid ,phoneNum );
        orderRepository.save(flightOrder);

        return orderRepository.save(flightOrder).getId();
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean orderPaid(String bankName,Integer orderId){
        FlightOrder flightOrder = orderRepository.findById(orderId).get();
        flightOrder.setPayBank(bankName);
        orderRepository.save(flightOrder);
        return true;
    }
    @Transactional(rollbackFor = Exception.class)
    public boolean drawback(String bankName,Integer orderId){
        orderRepository.deleteById(orderId);
        return true;
    }
}

