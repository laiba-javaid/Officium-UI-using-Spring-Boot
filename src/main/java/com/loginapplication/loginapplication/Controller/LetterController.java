package com.loginapplication.loginapplication.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LetterController {
    @GetMapping("/letter")
    public String letter() {
        return "letter";
    }
}
