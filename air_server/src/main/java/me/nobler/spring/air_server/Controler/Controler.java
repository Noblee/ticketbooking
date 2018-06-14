package me.nobler.spring.air_server.Controler;

import me.nobler.spring.air_server.DTO.FlightOrderDTO;
import me.nobler.spring.air_server.Entity.Bank;
import me.nobler.spring.air_server.Entity.Flight;
import me.nobler.spring.air_server.Entity.FlightOrder;
import me.nobler.spring.air_server.Repository.BankRepository;
import me.nobler.spring.air_server.Repository.FlightRepository;
import me.nobler.spring.air_server.Repository.OrderRepository;
import me.nobler.spring.air_server.Sevice.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Noble
 */
@RestController
public class Controler {

    @Value("${airlinename}")
    String airlinename;
    private final FlightRepository flightRepository;

    @Autowired
    public Controler(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private AirlineService airlineService;

    @Value( "${airlinename}" )
    private String myName;

    @GetMapping( "/getmyname" )
    public String getMyName() {
        return myName;
    }

    @GetMapping( "/admin/addFlight" )
    public Boolean addFlight(Flight flight) {
        try {
            flightRepository.save(flight);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @GetMapping( "/getFlights" )
    public List<Flight> getFlights() {
        List<Flight> flights = flightRepository.findAll();
        System.out.println(flights.toString());
        return flights;
    }

    @GetMapping( "/admin/deleteflight" )
    public boolean deleteFlight(Integer id) {
        flightRepository.deleteById(id);
        return true;
    }

    @GetMapping( "/orderflight" )
    public Integer orderFlight(Integer flightId, String uid) {
        return airlineService.addOrder(flightId, uid, "");
    }


    @GetMapping( "/admin/getorders" )
    public List<FlightOrder> getOrders() {
        return orderRepository.findAll();
    }

    @GetMapping( "/getbanks" )
    public List<String> getBanks() {

        List<Bank> banks = bankRepository.findAll();
        ArrayList<String> banknames = new ArrayList();
        for (Bank bank : banks) {
            banknames.add(bank.getName());
        }
        return banknames;
    }

    @GetMapping( "/getAllbanks" )
    public List<Bank> getAllBanks() {

        List<Bank> banks = bankRepository.findAll();
        for (Bank bank : banks) {
            bank.setPassword("");
        }
        return banks;
    }

    @GetMapping( "/getOrderById" )
    public FlightOrderDTO getOrderById(@Param( "id" ) Integer id) {
        FlightOrderDTO flightOrderDTO = airlineService.getOrderById(id);
        return flightOrderDTO;
    }

    @GetMapping( "/getOrderMoneyById" )
    public Integer getOrderMoneyById(@Param( "id" ) Integer id) {
        return   airlineService.getOrderById(id).getPrize();

    }

    @GetMapping( "/getFlightById" )
    public Flight getFlightById(@Param( "id" ) Integer id) {
        return airlineService.getFlightById(id);
    }

    @GetMapping( "/getBankUserByName" )
    public String getBankUserByName(String name) {
        try {
            return bankRepository.findById(name).get().getUsername();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping( "/admin/addBank" )
    public boolean addBank(Bank bank) {
        try {
            bankRepository.save(bank);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping( "/orderpaid" )
    public boolean orderPaid(String bankName, Integer orderId) {
        return airlineService.orderPaid(bankName, orderId);
    }

    @GetMapping( "/drawback" )
    public boolean drawback(String bankName, Integer orderId) {
        return airlineService.drawback(bankName, orderId);
    }
    @GetMapping( "/myname" )
    public String myName() {
        return airlinename;
    }
}
