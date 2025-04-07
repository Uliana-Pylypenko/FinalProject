package pl.coderslab.finalproject.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.finalproject.entity.User;
import pl.coderslab.finalproject.repository.UserRepository;
import pl.coderslab.finalproject.service.UserService;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class LoginController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "initial_login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, password);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            session.setAttribute("user", user);
            if (user.isAdmin()) {
                return "redirect:/admin/home";
            } else {
                return "redirect:/user/home";
            }

        } else {
            model.addAttribute("error", "Invalid username or password");
            return "initial_login"; // Return to login.jsp with error
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login"; // Redirect to login page
    }

}
