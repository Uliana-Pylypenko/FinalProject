package pl.coderslab.finalproject.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.dto.PlaceDTO;
import pl.coderslab.finalproject.dto.UserDTO;
import pl.coderslab.finalproject.entity.Place;
import pl.coderslab.finalproject.entity.User;
import pl.coderslab.finalproject.exception.DuplicateEmailException;
import pl.coderslab.finalproject.exception.DuplicateUserException;
import pl.coderslab.finalproject.repository.PlaceRepository;
import pl.coderslab.finalproject.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    public ResponseEntity<UserDTO> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return new ResponseEntity<>(UserDTO.toDTO(user.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> userDTOS = userRepository
                .findAll()
                .stream()
                .map(UserDTO::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    public ResponseEntity<UserDTO> getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(UserDTO.toDTO(user.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public void checkForDuplicates(UserDTO userDTO) {
        Optional<User> user1 = userRepository.findByUsername(userDTO.getUsername());
        Optional<User> user2 = userRepository.findByEmail(userDTO.getEmail());
        if (user1.isPresent() && user1.get().getId() != userDTO.getId()) {
            throw new DuplicateUserException("Username " + userDTO.getUsername() + " already exists");
        }
        if (user2.isPresent() && user2.get().getId() != userDTO.getId()) {
            throw new DuplicateEmailException("User with email " + userDTO.getEmail() + " already exists");
        }
    }


    public ResponseEntity<String> create(UserDTO userDTO) {
        checkForDuplicates(userDTO);
        userRepository.save(UserDTO.toEntity(userDTO, placeRepository));
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> update(Long id, UserDTO userDTO) {
        Optional<User> userToUpdate = userRepository.findById(id);
        checkForDuplicates(userDTO);
        if (userToUpdate.isPresent()) {
            User user = UserDTO.toEntity(userDTO, placeRepository);
            user.setId(id);
            userRepository.save(user);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> changeAdminRights(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setAdmin(!user.get().isAdmin());
            userRepository.save(user.get());
            return new ResponseEntity<>("User admin rights changed successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }




}
