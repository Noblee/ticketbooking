package me.nobler.spring.bank_server.Controler;

import me.nobler.spring.bank_server.Entity.User;
import me.nobler.spring.bank_server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
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

    @Value( "${bankname}" )
    String bankname;

    @PostMapping( "/login" )
    public String login(HttpSession session, @Param( "username" ) String username,
                        @Param( "password" ) String password) {
        if (!username.equals("admin"))
            return "不支持非管理员登陆";
        User user = userRepository.findById(username).get();
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
    public String signup(@Param( "username" ) String username,
                         @Param( "password" ) String password) {
        try {
            userRepository.findById(username).get();
        } catch (Exception e) {
            User user = new User(username, password);
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

    @GetMapping( "/myname" )
    public String myname() {
        return bankname;
    }
}
