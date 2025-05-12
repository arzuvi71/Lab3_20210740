package com.example.lab3_20210740.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectToHospitals() {
        return "redirect:/hospitales";
    }
}