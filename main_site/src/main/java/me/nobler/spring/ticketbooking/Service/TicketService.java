package me.nobler.spring.ticketbooking.Service;//package me.nobler.spring.bank_server.Sevice;

import me.nobler.spring.ticketbooking.DTO.FlightDTO;
import me.nobler.spring.ticketbooking.DTO.FlightComOrderDTO;
import me.nobler.spring.ticketbooking.DTO.OrderDTO;
import me.nobler.spring.ticketbooking.Entity.Flight;
import me.nobler.spring.ticketbooking.Entity.FlightOrder;
import me.nobler.spring.ticketbooking.Repository.FlightOrderRepository;
import me.nobler.spring.ticketbooking.TicketbookingApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


@Service
public class TicketService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FlightOrderRepository flightOrderRepository;
    public boolean payOrder(String username ,String password, String flightProvider,Integer ordrId,String bankName){

        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://" + flightProvider+
                "/getOrderMoneyById?id={1}").build();
        URI uri = uriComponents.expand(ordrId).encode().toUri();
        ResponseEntity<Integer> integerResponseEntity;
        try {
            integerResponseEntity = restTemplate.getForEntity(uri,Integer.class);
        } catch (Exception e) {
            return false;
        }
        int money = integerResponseEntity.getBody();
        uriComponents =  UriComponentsBuilder.fromUriString("http://" + bankName+
                "/airplane/payfororder?username={1}&password={2}&orderId={3}&airName={4}&money={5}").build();
        uri = uriComponents.expand(username,password,ordrId,flightProvider,money).encode().toUri();
        ResponseEntity<Boolean> booleanResponseEntity;
        try {
            booleanResponseEntity = restTemplate.getForEntity(uri,Boolean.class);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String[] getSupportBanks(String flightProvider){

        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://" + flightProvider+ "/getbanks").build();
        URI uri = uriComponents.encode().toUri();
        ResponseEntity<String[]> listResponseEntity;
        try {
            listResponseEntity = restTemplate.getForEntity(uri,String[].class);
        } catch (Exception e) {
            return null;
        }
       return listResponseEntity.getBody();
    }



    public List<FlightDTO> getFlights() {
        ArrayList<FlightDTO> flightDTOS = new ArrayList<>();
        for (String s : TicketbookingApplication.airProvider) {
            UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://" + s + "/getFlights").build();
            URI uri = uriComponents.encode().toUri();
            ResponseEntity<Flight[]> listResponseEntity;
            try {
                listResponseEntity = restTemplate.getForEntity(uri, Flight[].class);
            } catch (Exception e) {
                continue;
            }
            Flight[] flights = listResponseEntity.getBody();
            if (flights == null) {
                continue;
            }
            for (Flight flight : flights) {
                FlightDTO flightDTO = new FlightDTO(s, flight.getId(), flight.getDay(), flight.getDeparture(), flight.getDestination(), flight.getArrival(), flight.getGo_off(), flight.getPrize(), flight.getRemaining());
                flightDTOS.add(flightDTO);
            }
        }
        return flightDTOS;
    }

    public boolean orderflight(String username, String flightProvider, Integer flightId, String uid, String phoneNum) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://" + flightProvider + "/orderflight" +
                "?flightId={1}&uid={2}"
        ).build();

        URI uri = uriComponents.expand(flightId, uid).encode().toUri();
        ResponseEntity<Integer> integerResponseEntity;
        try {
            integerResponseEntity = restTemplate.getForEntity(uri, Integer.class);
        } catch (Exception e) {
            return false;
        }
        Integer oid = integerResponseEntity.getBody();
        FlightOrder flightOrder = new FlightOrder(username,flightProvider , oid);
        flightOrderRepository.save(flightOrder);
        return true;
    }

    public List<OrderDTO> getAllOrders(String username) {
        List<FlightOrder> flightOrders = flightOrderRepository.findByUsername(username);
        ArrayList<OrderDTO> orderDTOs = new ArrayList<>();
        for (FlightOrder flightOrder : flightOrders) {

            UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://"
                    + flightOrder.getFlightCom() +
                    "/getOrderById?id={1}").build();

            URI uri = uriComponents.expand(flightOrder.getFlightOid()).encode().toUri();
            ResponseEntity<FlightComOrderDTO> flightOrderDTOResponseEntity;
            try {
                flightOrderDTOResponseEntity = restTemplate.getForEntity(uri, FlightComOrderDTO.class);
            } catch (Exception e) {
                continue;
            }
            FlightComOrderDTO flightComOrderDTO = flightOrderDTOResponseEntity.getBody();
            OrderDTO orderDTO = new OrderDTO(flightOrder.getId(), flightOrder.getFlightCom(),
                    flightOrder.getFlightOid(), flightComOrderDTO.getFlightId(), flightComOrderDTO.getDay(),
                    flightComOrderDTO.getDeparture(), flightComOrderDTO.getDestination(),
                    flightComOrderDTO.getGo_off(), flightComOrderDTO.getArrival()
                    , flightComOrderDTO.getPrize(), flightComOrderDTO.getPayBank());
            orderDTOs.add(orderDTO);
        }
        return orderDTOs;

    }

}

