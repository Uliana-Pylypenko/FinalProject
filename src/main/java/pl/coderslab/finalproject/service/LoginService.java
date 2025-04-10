package pl.coderslab.finalproject.service;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.dto.UserDTO;
import pl.coderslab.finalproject.entity.User;
import pl.coderslab.finalproject.exception.DuplicateUserException;
import pl.coderslab.finalproject.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {
    private final UserService userService;
    private final UserRepository userRepository;

    public String register(String username, String email, String password) {
        Optional<User> user1 = userRepository.findByUsername(username);
        Optional<User> user2 = userRepository.findByEmail(email);

        if (user1.isPresent()) {
            throw new DuplicateUserException("Username " + username + " already exists");
        } else if (user2.isPresent()) {
            throw new DuplicateUserException("Username " + username + " already exists");
        } else {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(username);
            userDTO.setEmail(email);
            userDTO.setPassword(hashPassword(password));
            userDTO.setAdmin(false);
            userService.create(userDTO);
            return "redirect:/login";
        }

    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String plaintextPassword, String hashedPassword) {
        return BCrypt.checkpw(plaintextPassword, hashedPassword);
    }
}
