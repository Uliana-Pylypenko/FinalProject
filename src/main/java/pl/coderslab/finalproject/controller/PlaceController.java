package pl.coderslab.finalproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.dto.CategoryDTO;
import pl.coderslab.finalproject.dto.PlaceDTO;
import pl.coderslab.finalproject.dto.UserDTO;
import pl.coderslab.finalproject.exception.DuplicatePlaceNameException;
import pl.coderslab.finalproject.repository.PlaceRepository;
import pl.coderslab.finalproject.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/place")
@AllArgsConstructor
public class PlaceController {
    private final PlaceService placeService;
    private final CategoryService categoryService;
    private final PlaceDetailsService placeDetailsService;
    private final EventService eventService;

    @GetMapping()
    public String getFilteredPlaces(Model model, HttpServletRequest request) {
        model.addAttribute("places", placeService.getAllDTO().getBody());
        List<CategoryDTO> categoryDTOS = categoryService.getAll().getBody();
        model.addAttribute("all_categories", categoryDTOS);

        List<PlaceDTO> filteredPlaces = filterPlaces(request, model);
        model.addAttribute("places", filteredPlaces);

        return "places";
    }

    public List<PlaceDTO> filterPlaces(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String activity = request.getParameter("activity");

        List<CategoryDTO> checkedCategories = new ArrayList<>();
        for (CategoryDTO categoryDTO : categoryService.getAll().getBody()) {
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
        return filteredPlaces;
    }

    @GetMapping("/map")
    public String getFilteredPlacesMap(HttpServletRequest request, Model model) {
        List<PlaceDTO> placeDTOS = placeService.getAllDTO().getBody();
        JSONArray jsonArray = placesToJSON(placeDTOS);
        model.addAttribute("places", jsonArray.toString());
        List<CategoryDTO> categoryDTOS = categoryService.getAll().getBody();
        model.addAttribute("all_categories", categoryDTOS);

        List<PlaceDTO> filteredPlaces = filterPlaces(request, model);
        model.addAttribute("places", placesToJSON(filteredPlaces).toString());

        return "map_places";
    }

    public JSONArray placesToJSON(List<PlaceDTO> placeDTOS) {
        if (placeDTOS == null) {
            return null;
        } else {
            JSONArray jsonArray = new JSONArray();
            for (PlaceDTO placeDTO : placeDTOS) {
                JSONObject placeJson = new JSONObject();
                placeJson.put("latitude", placeDTO.getLatitude());
                placeJson.put("longitude", placeDTO.getLongitude());
                jsonArray.put(placeJson);
            }
            return jsonArray;
        }

    }


    @GetMapping("/{id}")
    public ResponseEntity<PlaceDTO> getById(@PathVariable Long id) {
        return placeService.getByIdDTO(id);
    }

    @GetMapping("/create")
    public String create(HttpSession session) {
        session.setAttribute("categories", categoryService.getAll().getBody());
        return "initial_add_place";
    }

    @PostMapping("/create")
    public String create(HttpServletRequest request, Model model) {
        PlaceDTO placeDTO = null;
        try {
            placeDTO = placeDTOForm(request, model);
        } catch (NumberFormatException e) {
            model.addAttribute("error_message", "Enter valid latitude and longitude and choose a category");
            return "initial_add_place";
        }
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

            String role = userDTO.isAdmin() ? "admin" : "user";
            return "redirect:/" + role + "/home";


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
    public String update(@PathVariable Long id, Model model, HttpSession session) {
        session.setAttribute("categories", categoryService.getAll().getBody());
        model.addAttribute("current_place", placeService.getByIdDTO(id).getBody());
        return "initial_add_place";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, HttpServletRequest request, Model model) {
        try {
            PlaceDTO placeDTO = placeDTOForm(request, model);
            placeDTO.setId(id);
            placeService.update(id, placeDTO);
            return "redirect:/place-details/place-id/" + id;
        } catch (DuplicatePlaceNameException e) {
            model.addAttribute("error_message", e.getMessage());
        } catch (NumberFormatException e2) {
            model.addAttribute("error_message", "Enter valid latitude and longitude and choose a category");
        }
        model.addAttribute("current_place", placeService.getByIdDTO(id).getBody());
        return "initial_add_place";
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

    public PlaceDTO placeDTOForm(HttpServletRequest request, Model model) {
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
