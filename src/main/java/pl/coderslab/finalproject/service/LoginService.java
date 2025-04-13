package pl.coderslab.finalproject.service;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
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
        userDTO.setPassword(hashPassword(password));
        userDTO.setAdmin(false);
        userService.create(userDTO);
        return "redirect:/login";
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String plaintextPassword, String hashedPassword) {
        return BCrypt.checkpw(plaintextPassword, hashedPassword);
    }
}
