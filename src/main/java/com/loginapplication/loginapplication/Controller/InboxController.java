package com.loginapplication.loginapplication.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InboxController {
    @GetMapping("/inbox")
    public String inbox() {
        return "inbox";
    }

}
