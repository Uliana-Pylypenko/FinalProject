package pl.coderslab.finalproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.dto.CategoryDTO;
import pl.coderslab.finalproject.dto.PlaceDTO;
import pl.coderslab.finalproject.dto.UserDTO;
import pl.coderslab.finalproject.entity.Category;
import pl.coderslab.finalproject.repository.PlaceRepository;
import pl.coderslab.finalproject.service.CategoryService;
import pl.coderslab.finalproject.service.PlaceService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/place")
@AllArgsConstructor
public class PlaceController {
    private final PlaceService placeService;
    private final CategoryService categoryService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("places", placeService.getAll());
        return "initial_places";
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceDTO> getById(@PathVariable Long id) {
        return placeService.getByIdDTO(id);
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("categories", categoryService.getAll().getBody());
        return "initial_add_place";
    }

    @PostMapping("/create")
    public String create(HttpServletRequest request, Model model) {
        PlaceDTO placeDTO = new PlaceDTO();
        String name = request.getParameter("name");
        double latitude = Double.parseDouble(request.getParameter("latitude"));
        double longitude = Double.parseDouble(request.getParameter("longitude"));
        Long categoryId = Long.parseLong(request.getParameter("category"));
        CategoryDTO categoryDTO = categoryService.getCategoryById(categoryId);
        HttpSession session = request.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
        Long userId = userDTO.getId();

        placeDTO.setName(name);
        placeDTO.setLatitude(latitude);
        placeDTO.setLongitude(longitude);
        placeDTO.setCategoryDTO(categoryDTO);
        placeDTO.setUserId(userId);

        ResponseEntity<String> createResponse = placeService.create(placeDTO);

        if (createResponse.getStatusCode() == HttpStatus.CREATED) {

            List<PlaceDTO> placeDTOS = (List<PlaceDTO>) session.getAttribute("userPlaces");
            if (placeDTOS == null) {
                placeDTOS = new ArrayList<>();
            }
            placeDTOS.add(placeDTO);

            session.setAttribute("userPlaces", placeDTOS);

            return "redirect:/user/home";
        } else {
            model.addAttribute("error_message", "Can't create place");
            return "error";
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody PlaceDTO placeDTO) {
        return placeService.update(id, placeDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return placeService.delete(id);
    }

//    @GetMapping("/categories")
//    public List<Category> getAllCategories() {
//        return placeService.getAllCategories();
//    }
}
