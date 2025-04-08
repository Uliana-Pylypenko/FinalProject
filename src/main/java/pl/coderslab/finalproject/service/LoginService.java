package pl.coderslab.finalproject.service;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.finalproject.dto.PlaceDTO;
import pl.coderslab.finalproject.dto.PlaceDetailsDTO;
import pl.coderslab.finalproject.dto.UserDTO;
import pl.coderslab.finalproject.entity.User;
import pl.coderslab.finalproject.repository.PlaceDetailsRepository;
import pl.coderslab.finalproject.repository.PlaceRepository;
import pl.coderslab.finalproject.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final PlaceDetailsRepository placeDetailsRepository;

    public String login(String username, String password, HttpSession session, Model model) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, password);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDTO userDTO = UserDTO.toDTO(user);
            session.setAttribute("userDTO", userDTO);
            List<PlaceDTO> placeDTOS = new ArrayList<>();
            List<PlaceDetailsDTO> placeDetailsDTOS = new ArrayList<>();
            if (userDTO.getPlaceIds() != null) {
                placeDTOS = placeRepository.findAllById(userDTO.getPlaceIds())
                        .stream()
                        .map(PlaceDTO::toDTO)
                        .collect(Collectors.toList());

                List<Long> placeDetailsIds = placeDTOS
                        .stream()
                        .map(PlaceDTO::getDetailsId)
                        .collect(Collectors.toList());
                placeDetailsDTOS = placeDetailsRepository
                        .findAllById(placeDetailsIds)
                        .stream()
                        .map(PlaceDetailsDTO::toDTO)
                        .collect(Collectors.toList());
            }
            session.setAttribute("userPlaceDTOS", placeDTOS);
            session.setAttribute("userPlaceDetailsDTOS", placeDetailsDTOS);

            //store also user places, details and evets DTOs
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
