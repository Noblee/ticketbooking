package me.nobler.spring.bank_server.Controler;

import me.nobler.spring.bank_server.Entity.User;
import me.nobler.spring.bank_server.EurekaClientApplication;
import me.nobler.spring.bank_server.Repository.UserRepository;
import me.nobler.spring.bank_server.Sevice.AirlineService;
import me.nobler.spring.bank_server.Sevice.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@RestController
public class AirControler {
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    @Autowired
    private TransService transService;
    @Autowired
    AirlineService airlineService;
    @Autowired
    public AirControler(RestTemplate restTemplate, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    @Value("${bankname}")
    String bankname;
    @GetMapping( "/airplane/payfororder" )
    public boolean payForOrder(String username,String password, Integer orderId, String airName, Integer money) throws
            Exception {

        User userdata;
        try {
            userdata = userRepository.findById(username).get();
        } catch (Exception e) {
            return false;
        }
        if (!password.equals(userdata.getPassword())) {
            return false;
        }

        ResponseEntity<String> stringResponseEntity = restTemplate.getForEntity
                ("http://"+airName+"/getBankUserByName?name={1}", String.class, bankname);
        String body = stringResponseEntity.getBody();
        User airlineAcc = userRepository.findById(body).get();
        return airlineService.payForAirline(airName, userdata, airlineAcc, orderId, money);
    }

    @GetMapping( "/airplane/drawback" )
    public boolean drawback(HttpSession session, Integer orderId, String airName, Integer money) throws Exception {
        User user = (User) session.getAttribute("user");
        AirlineService airlineService = new AirlineService();
        ResponseEntity<String> stringResponseEntity = restTemplate.getForEntity
                ("http://AIR-SERVER/getBankUserByName?name={1}", String.class, airName);
        String body = stringResponseEntity.getBody();
        User airlineAcc = userRepository.findById(body).get();
        return airlineService.payForAirline(airName, user, airlineAcc, orderId, money);
    }

    @GetMapping( "/admin/getBalance" )
    public Integer getBalance(HttpSession session) {
        return transService.getBalance(((User) session.getAttribute("user")));
    }

    @GetMapping( "/admin/deposit" )
    public Boolean deposit(String username, Integer money, String info) {
        return transService.deposit(userRepository.findById(username).get(), money, info);
    }

    @GetMapping( "/admin/withdraw" )
    public Boolean withdraw(String username, Integer money, String info) {
        return transService.withdraw(userRepository.findById(username).get(), money, info);

    }

    @GetMapping( "/admin/delete" )
    public Boolean delete(String username) {
        userRepository.deleteById(username);
        return true;
    }

    @GetMapping( "/admin/transfer" )
    public Boolean transfer(HttpSession session, String username, Integer money, String info) {
        return transService.transfer(userRepository.findById(username).get(), username, money, info);

    }

}
