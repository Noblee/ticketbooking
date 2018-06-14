package me.nobler.spring.ticketbooking.Controler;

import me.nobler.spring.ticketbooking.Entity.User;
import me.nobler.spring.ticketbooking.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@RestController
public class LogControler {
    private final UserRepository userRepository;

    @Autowired
    public LogControler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping( "/login" )
    public String login(HttpSession session, User user) {
        session.setAttribute("user", user);
        User userdata;
        try {
            userdata = userRepository.findById(user.getUsername()).get();
        } catch (Exception e) {
            return "error";
        }
        if (!user.getPassword().equals(userdata.getPassword())) {
            return "error";
        }
        session.setAttribute("userdata", userdata);
        return "OK";
    }

    @PostMapping( "/signup" )
    public String signup(User user) {
        try {
            userRepository.findById(user.getUsername()).get();
        } catch (Exception e) {
            userRepository.save(user);
            return "OK";
        }
        return "error";
    }

    @GetMapping( "/quit" )
    public ModelAndView quit(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/");

    }


}
