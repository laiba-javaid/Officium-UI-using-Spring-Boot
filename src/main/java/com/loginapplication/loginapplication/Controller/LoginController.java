package com.loginapplication.loginapplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.loginapplication.loginapplication.model.User;
import com.loginapplication.loginapplication.repository.UserRepository;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "index"; // Return the name of your login page template (e.g., index.html)
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // Check if username or password is empty
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Username and password must not be empty");
            return "index";
        }

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                model.addAttribute("username", username);
                return "welcome"; // Return the welcome page if login is successful
            } else {
                
                return "index"; // Return the login page with error if the password is wrong
            }
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "index"; // Return the login page with error if the username doesn't exist
        }
    }
}
