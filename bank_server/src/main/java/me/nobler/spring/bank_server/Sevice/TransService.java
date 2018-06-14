package me.nobler.spring.bank_server.Sevice;

import me.nobler.spring.bank_server.Entity.TransLog;
import me.nobler.spring.bank_server.Entity.TransType;
import me.nobler.spring.bank_server.Entity.User;
import me.nobler.spring.bank_server.Repository.TransLogRepository;
import me.nobler.spring.bank_server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
public class TransService {
    @Autowired

    private  UserRepository userRepository;
    @Autowired

    private  TransLogRepository transLogRepository;


    @Transactional( rollbackFor = Exception.class )
    public Integer getBalance(User userdata) {
        return userdata.getBalance();
    }

    @Transactional( rollbackFor = Exception.class )
    public boolean deposit(User userdata, Integer money, String info) {
        if (money <= 0) {
            return false;
        }
        userdata.setBalance(userdata.getBalance() + money);
        userRepository.save(userdata);
        TransLog transLog = new TransLog(TransType.DEPOSIT, userdata.getUsername(), null, money, new Date(new java.util.Date().getTime()), info);
        transLogRepository.save(transLog);
        return true;
    }

    @Transactional( rollbackFor = Exception.class )
    public boolean withdraw(User userdata, Integer money, String info) {
        if (userdata.getBalance() < money) {
            return false;
        } else {
            userdata.setBalance(userdata.getBalance() - money);
            userRepository.save(userdata);
            TransLog transLog = new TransLog(TransType.WITHDRAW, userdata.getUsername(), null, money, new Date(new java.util.Date
                    ().getTime()), info);
            transLogRepository.save(transLog);
            return true;
        }
    }

    @Transactional( rollbackFor = Exception.class )
    public boolean transfer(User userdata, String username, Integer money, String info) {
        User user;
        try {
            user = userRepository.findById(username).get();
        } catch (Exception e) {
            return false;
        }
        if (userdata.getBalance() < money) {
            return false;
        } else {
            userdata.setBalance(userdata.getBalance() - money);
            userRepository.save(userdata);
            user.setBalance(user.getBalance() + money);
            userRepository.save(user);
            userRepository.save(userdata);
            TransLog transLog = new TransLog(TransType.TRANSFER, userdata.getUsername(), username,money,  new Date(new java.util
                    .Date
                    ().getTime()), info);
            transLogRepository.save(transLog);
            return true;
        }
    }

//    @Transactional
//    boolean PayforAirline(User userdata,,Integer money)
}
