package pl.coderslab.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.finalproject.entity.Event;
import pl.coderslab.finalproject.entity.Place;
import pl.coderslab.finalproject.entity.User;
import pl.coderslab.finalproject.repository.PlaceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean isAdmin;
    private List<Long> placeIds;

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAdmin(user.isAdmin());
        List<Long> placeIds = user
                .getPlaces()
                .stream()
                .map(Place::getId)
                .collect(Collectors.toList());
        userDTO.setPlaceIds(placeIds);
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO, PlaceRepository placeRepository) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAdmin(userDTO.isAdmin());
        List<Long> placeIds = userDTO.getPlaceIds();
        if (placeIds != null) {
            user.setPlaces(placeRepository.findAllById(userDTO.getPlaceIds()));
        } else {
            user.setPlaces(null);
        }
        return user;
    }
}
