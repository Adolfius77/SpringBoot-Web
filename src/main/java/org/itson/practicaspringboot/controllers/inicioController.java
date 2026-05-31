package org.itson.practicaspringboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class inicioController {

    @RequestMapping("/")
    public String home() {
        return "index.html";
    }


}




