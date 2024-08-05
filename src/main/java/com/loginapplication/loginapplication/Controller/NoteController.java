package com.loginapplication.loginapplication.Controller;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class NoteController {

    @GetMapping("/note")
    public String note() {
       
        return "note";
    }
}
