package com.loginapplication.loginapplication.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DraftController {

    @GetMapping("/draft")
    public String draft() {
        return "draft";
    }

}
