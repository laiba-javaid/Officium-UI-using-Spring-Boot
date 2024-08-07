package com.loginapplication.loginapplication.Controller;

import com.loginapplication.loginapplication.model.User;
import com.loginapplication.loginapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String password, @RequestParam String email,
            Model model) {
        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "signup";
        }

        if (userRepository.findByEmail(email).isPresent()) {
            model.addAttribute("error", "Email already exists");
            return "signup";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        userRepository.save(user);

        model.addAttribute("username", username);
        return "welcome";
    }
}
