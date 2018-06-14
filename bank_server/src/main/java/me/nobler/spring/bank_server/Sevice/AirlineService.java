package me.nobler.spring.bank_server.Sevice;

import me.nobler.spring.bank_server.Entity.User;
import me.nobler.spring.bank_server.EurekaClientApplication;
import me.nobler.spring.bank_server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class AirlineService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    TransService transService;
    @Value( "${bankname}" )
    String bankname;

    //转账和通知航空公司付款必须是事务的
    @Transactional( rollbackFor = Exception.class )
    public boolean payForAirline(String airName, User user, User airlineAcc, Integer orderNum, Integer money) throws Exception {
        if (!transService.transfer(user, airlineAcc.getUsername(), money, "Pay For " + airName + ":" + orderNum.toString())) {
            throw new Exception("转账错误");
        }
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(
                "http://" + airName + "/orderpaid?bankName={1}&orderId={2}").build();
        URI uri = uriComponents.expand(bankname, orderNum).encode().toUri();
        ResponseEntity<Boolean> stringResponseEntity = restTemplate.getForEntity(uri, Boolean.class);
        Boolean body = stringResponseEntity.getBody();
        if (body == null || !body) {
            throw new Exception("通知错误");
        }
        return true;
    }

    @Transactional( rollbackFor = Exception.class )
    public boolean drawbackForAirline(String airName, User airlineUser, User userAcc, Integer orderNum, Integer money)
            throws Exception {
        if (!transService.transfer(airlineUser, userAcc.getUsername(), money, "Drawback For " + airName + ":" + orderNum
                .toString())) {
            throw new Exception("转账错误");
        }
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(
                "http://AIR-SERVER/drowback?bankName={1}&orderId={2}").build();
        URI uri = uriComponents.expand(bankname, orderNum).encode().toUri();
        ResponseEntity<Boolean> stringResponseEntity = restTemplate.getForEntity(uri, Boolean.class);
        Boolean body = stringResponseEntity.getBody();
        if (body == null || !body) {
            throw new Exception("通知错误");
        }
        return true;
    }
}

