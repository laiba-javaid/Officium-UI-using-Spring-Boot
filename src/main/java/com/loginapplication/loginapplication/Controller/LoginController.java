package com.loginapplication.loginapplication.Controller;

import com.loginapplication.loginapplication.model.User;
import com.loginapplication.loginapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) {
            model.addAttribute("username", username);
            return "welcome";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "index";
        }
    }
}
