package me.nobler.spring.bank_server.Controler;

import me.nobler.spring.bank_server.Entity.TransLog;
import me.nobler.spring.bank_server.Entity.User;
import me.nobler.spring.bank_server.Repository.TransLogRepository;
import me.nobler.spring.bank_server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransControler {
    private final UserRepository userRepository;
    private final TransLogRepository transLogRepository;

    @Autowired
    public TransControler(UserRepository userRepository, TransLogRepository transLogRepository) {
        this.userRepository = userRepository;
        this.transLogRepository = transLogRepository;
    }

    @GetMapping( "/admin/getusers" )
    @CrossOrigin
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        for (User u : users) {
            u.setPassword("");
        }
        return users;
    }

    @GetMapping( "/admin/gettranslogs" )
    @CrossOrigin
    public List<TransLog> getTransLog() {
        return transLogRepository.findAll();
    }
}
