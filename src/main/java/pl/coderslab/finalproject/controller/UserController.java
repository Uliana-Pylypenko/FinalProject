package pl.coderslab.finalproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/update/{id}")
    public String update() {
        return "initial_update_profile";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserDTO userToUpdate = (UserDTO) session.getAttribute("userDTO");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (userToUpdate.getPassword().equals(password)) {
            userToUpdate.setUsername(username);
            userToUpdate.setEmail(email);
            ResponseEntity<String> updateResponse = userService.update(id, userToUpdate);
            if (updateResponse.getStatusCode().is2xxSuccessful()) {
                session.setAttribute("userDTO", userToUpdate);
                return "redirect:/user/home";
            } else {
                model.addAttribute("error_message", "Error updating user");
                return "error";
            }
        } else {
            model.addAttribute("error_message", "Wrong password");
            return "error";
        }
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
