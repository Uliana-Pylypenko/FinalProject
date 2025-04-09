package pl.coderslab.finalproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.dto.CategoryDTO;
import pl.coderslab.finalproject.dto.PlaceDTO;
import pl.coderslab.finalproject.dto.UserDTO;
import pl.coderslab.finalproject.entity.Category;
import pl.coderslab.finalproject.entity.Place;
import pl.coderslab.finalproject.repository.PlaceRepository;
import pl.coderslab.finalproject.service.CategoryService;
import pl.coderslab.finalproject.service.PlaceService;
import pl.coderslab.finalproject.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/place")
@AllArgsConstructor
public class PlaceController {
    private final PlaceService placeService;
    private final CategoryService categoryService;
    private final PlaceRepository placeRepository;

    @GetMapping()
    public String getFilteredPlaces(Model model, HttpServletRequest request) {
        model.addAttribute("places", placeService.getAllDTO().getBody());
        List<CategoryDTO> categoryDTOS = categoryService.getAll().getBody();
        model.addAttribute("all_categories", categoryDTOS);

        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String activity = request.getParameter("activity");

        List<CategoryDTO> checkedCategories = new ArrayList<>();
        for (CategoryDTO categoryDTO : categoryDTOS) {
            if (request.getParameter(categoryDTO.getName()) != null) {
                checkedCategories.add(categoryDTO);
            }
        }

        model.addAttribute("filter_name", name);
        model.addAttribute("filter_country", country);
        model.addAttribute("filter_city", city);
        model.addAttribute("filter_activity", activity);
        model.addAttribute("filter_category", checkedCategories);
        
        List<PlaceDTO> filteredPlaces = placeService.getFilteredPlaces(name, country, city, activity, checkedCategories).getBody();
        model.addAttribute("places", filteredPlaces);
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
        PlaceDTO placeDTO = placeDTOForm(request);
        HttpSession session = request.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
        Long userId = userDTO.getId();
        placeDTO.setUserId(userId);

        ResponseEntity<String> createResponse = placeService.create(placeDTO);

        if (createResponse.getStatusCode() == HttpStatus.CREATED) {

            List<PlaceDTO> placeDTOS = (List<PlaceDTO>) session.getAttribute("userPlaces");
            if (placeDTOS == null) {
                placeDTOS = new ArrayList<>();
            }
            placeDTOS.add(placeService.getLastElement());

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

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("categories", categoryService.getAll().getBody());
        model.addAttribute("current_place", placeService.getByIdDTO(id).getBody());
        return "initial_add_place";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, HttpServletRequest request) {
        PlaceDTO placeDTO = placeDTOForm(request);
        placeService.update(id, placeDTO);
        return "redirect:/place-details/place-id/" + id;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return placeService.delete(id);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        String message = "Are you sure you want to delete this place and all events related to it?";
        model.addAttribute("delete_message", message);
        return "initial_delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, HttpServletRequest request) {
        boolean answer = Boolean.parseBoolean(request.getParameter("answer"));
        if (answer) {
            placeService.delete(id);
            HttpSession session = request.getSession();
            List<PlaceDTO> placeDTOS = (List<PlaceDTO>) session.getAttribute("userPlaces");
            PlaceDTO placeDTO = placeDTOS.stream().filter(placeDTO1 -> placeDTO1.getId() == id).findFirst().get();
            placeDTOS.remove(placeDTO);
            session.setAttribute("userPlaces", placeDTOS);
            return "redirect:/user/home";
        } else {
            return "redirect:/place-details/place-id/" + id;
        }
    }

    public PlaceDTO placeDTOForm(HttpServletRequest request) {
        PlaceDTO placeDTO = new PlaceDTO();
        String name = request.getParameter("name");
        double latitude = Double.parseDouble(request.getParameter("latitude"));
        double longitude = Double.parseDouble(request.getParameter("longitude"));
        Long categoryId = Long.parseLong(request.getParameter("category"));
        CategoryDTO categoryDTO = categoryService.getCategoryById(categoryId);
        placeDTO.setName(name);
        placeDTO.setLatitude(latitude);
        placeDTO.setLongitude(longitude);
        placeDTO.setCategoryDTO(categoryDTO);
        return placeDTO;
    }


//    @GetMapping("/categories")
//    public List<Category> getAllCategories() {
//        return placeService.getAllCategories();
//    }
}
