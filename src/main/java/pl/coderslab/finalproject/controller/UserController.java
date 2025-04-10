package pl.coderslab.finalproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.dto.PlaceDTO;
import pl.coderslab.finalproject.dto.UserDTO;
import pl.coderslab.finalproject.exception.DuplicateEmailException;
import pl.coderslab.finalproject.exception.DuplicateUserException;
import pl.coderslab.finalproject.service.LoginService;
import pl.coderslab.finalproject.service.PlaceService;
import pl.coderslab.finalproject.service.UserService;

import java.util.List;

@Controller  // only @Controller works with the views
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final PlaceService placeService;
    private final LoginService loginService;

    @GetMapping("/user/home")
    public String userHome() {
        return "initial_user_profile";
    }

    @GetMapping("/admin/home")
    public String adminHome() {
        return "initial_admin_profile";
    }

//    @GetMapping("/user/username/{username}")
//    public String getUser(@PathVariable String username, Model model) {
//        model.addAttribute("user", userService.findByUsername(username));
//        return "initial_user_profile";
//    }

    @GetMapping("/admin/users")
    public String getAll(Model model) {
        model.addAttribute("all_users", userService.getAll().getBody());
        return "initial_users";
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping("/user/create")
    public ResponseEntity<String> create(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @GetMapping("/user/update/{id}")
    public String update() {
        return "initial_update_profile";
    }

    @PostMapping("/user/update/{id}")
    public String update(@PathVariable Long id, HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        UserDTO userToUpdate = (UserDTO) session.getAttribute("userDTO");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (loginService.checkPassword(password, userToUpdate.getPassword())) {
            UserDTO updatedUser = new UserDTO();
            updatedUser.setId(id);
            updatedUser.setUsername(username);
            updatedUser.setEmail(email);
            updatedUser.setPassword(userToUpdate.getPassword());
            updatedUser.setAdmin(userToUpdate.isAdmin());
            updatedUser.setPlaceIds(userToUpdate.getPlaceIds());

            try {
                userService.update(id, updatedUser);
                session.setAttribute("userDTO", updatedUser);
                return "redirect:/user/home";
            } catch (DuplicateUserException | DuplicateEmailException e ) {
                session.setAttribute("userDTO", userToUpdate);
                model.addAttribute("error_message", e.getMessage());
            }
        } else {
            model.addAttribute("error_message", "Wrong password");
        }


        return "initial_update_profile";
    }

    @GetMapping("/admin/change-admin-rights/{id}")
    public String changeAdminRights(@PathVariable Long id, Model model) {
        ResponseEntity<String> response = userService.changeAdminRights(id);
        if (! response.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("error_message", "Error changing admin rights");
        }
        return "redirect:/admin/users";
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @GetMapping("/admin/places-to-approve")
    public String placesToApprove(Model model) {
        model.addAttribute("places", placeService.getNotApprovedPlaces());
        return "initial_approve";
    }

    @GetMapping("/admin/approve/{id}")
    public String approve(@PathVariable Long id, HttpServletRequest request, Model model) {
        ResponseEntity<String> response = placeService.approvePlace(id);
        if (! response.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("error_message", "Error approving place");
        }
        return "redirect:/admin/places-to-approve";
    }

}
