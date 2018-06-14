package me.nobler.spring.ticketbooking.Controler;

import me.nobler.spring.ticketbooking.DTO.FlightDTO;
import me.nobler.spring.ticketbooking.DTO.OrderDTO;
import me.nobler.spring.ticketbooking.Entity.Flight;
import me.nobler.spring.ticketbooking.Entity.User;
import me.nobler.spring.ticketbooking.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTML;
import java.util.List;

@RestController
public class MainControler {
    final
    TicketService ticketService;

    @Autowired
    public MainControler(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("getflights")
    List<FlightDTO>   getFlights(){
        return ticketService.getFlights();
    }
    @GetMapping("/orderflight")
    public boolean orderFlight(HttpSession session, String flightProvider,Integer id,String uid) {
        User user = (User) session.getAttribute("user");
        return ticketService.orderflight(user.getUsername(),flightProvider,id ,uid, "");
    }
//    $.getJSON('./getsupportbank?flightProvider=' + parent.$('#pagedata').attr("flightProvider"), function (json) {

    @GetMapping("/getsupportbank")
    public String[] getSupportBank( String flightProvider) {
        return ticketService.getSupportBanks(flightProvider);
    }

    @GetMapping("/payorder")
    public boolean payOrder(String username ,String password, String flightProvider,Integer orderId,String bankName) {
        return ticketService.payOrder(username,password ,flightProvider , orderId, bankName);
    }


    @GetMapping("/getorders")
    public List<OrderDTO> orderFlight(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return ticketService.getAllOrders(user.getUsername());
    }
}
