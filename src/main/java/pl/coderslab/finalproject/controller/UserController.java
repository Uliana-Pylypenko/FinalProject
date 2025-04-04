package pl.coderslab.finalproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.dto.UserDTO;
import pl.coderslab.finalproject.service.UserService;

import java.util.List;

@Controller  // only @Controller works with the views
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/username/{username}")
    public String getUser(@PathVariable String username, Model model) {
        model.addAttribute("user", userService.findByUsername(username));
        return "initial_user_profile";
    }

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
