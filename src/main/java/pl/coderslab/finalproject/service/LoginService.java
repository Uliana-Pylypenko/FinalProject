package pl.coderslab.finalproject.service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.dto.UserDTO;

@Service
@AllArgsConstructor
public class LoginService {
    private final UserService userService;

    public String register(String username, String email, String password) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setAdmin(false);
        userService.create(userDTO);
        return "redirect:/login";
    }
}
