package com.io.ziblox.CinePass.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/api/v1/movies?page=1&limit=10"; // Redirect to a specific endpoint, e.g., movies list
    }
}
