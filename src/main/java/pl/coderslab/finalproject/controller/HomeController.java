package pl.coderslab.finalproject.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
@AllArgsConstructor
public class HomeController {
    @GetMapping
    public String home(HttpSession session) {
        return "home";
    }

    @GetMapping("/map")
    public String map() {
        return "map_places_nearby";
    }


}
