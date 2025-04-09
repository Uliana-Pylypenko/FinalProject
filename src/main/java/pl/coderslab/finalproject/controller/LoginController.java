package pl.coderslab.finalproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.finalproject.dto.UserDTO;
import pl.coderslab.finalproject.entity.User;
import pl.coderslab.finalproject.repository.UserRepository;
import pl.coderslab.finalproject.service.LoginService;
import pl.coderslab.finalproject.service.UserService;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class LoginController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginPage() {
        return "initial_login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        return loginService.login(username, password, session, model);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login"; // Redirect to login page
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access_denied";
    }

    @GetMapping("/register")
    public String register() {
        return "initial_registration";
    }

    @PostMapping("/register")
    public String register(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordCheck = request.getParameter("password_check");

        if (password.equals(passwordCheck)) {
            return loginService.register(username, email, password);
        } else {
            model.addAttribute("error_message", "Passwords do not match");
            return "initial_registration";
        }

    }

}
