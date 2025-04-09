package pl.coderslab.finalproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {
    @GetMapping
    public String home() {
        return "initial_home";
    }

    @GetMapping("/map")
    public String map() {
        return "map_places_nearby";
    }

    @GetMapping("/user/home")
    public String userHome() {
        return "initial_user_profile";
    }

    @GetMapping("/admin/home")
    public String adminHome() {
        return "admin_home";
    }
}
