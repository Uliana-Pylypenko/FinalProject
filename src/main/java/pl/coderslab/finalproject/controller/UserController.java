package pl.coderslab.finalproject.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.dto.UserDTO;
import pl.coderslab.finalproject.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.update(id, userDTO);
    }

    @PutMapping("/change-admin-rights/{id}")
    public ResponseEntity<String> changeAdminRights(@PathVariable Long id) {
        return userService.changeAdminRights(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return userService.delete(id);
    }

}
