package pl.coderslab.finalproject.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class HelloController {

//    @GetMapping({"/hello"})
//    public String hello(@RequestParam(value = "name", defaultValue = "World",
//            required = true) String name, Model model) {
//        model.addAttribute("name", name);
//        return "place_details";
//    }
}
