package pl.coderslab.finalproject.service;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.finalproject.entity.User;
import pl.coderslab.finalproject.repository.PlaceRepository;
import pl.coderslab.finalproject.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    public String login(String username, String password, HttpSession session, Model model) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, password);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            session.setAttribute("user", user);
            // store also user places, details and evets DTOs
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
}
