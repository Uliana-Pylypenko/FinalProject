package pl.coderslab.finalproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.dto.PlaceDTO;
import pl.coderslab.finalproject.dto.PlaceDetailsDTO;
import pl.coderslab.finalproject.dto.UserDTO;
import pl.coderslab.finalproject.service.EventService;
import pl.coderslab.finalproject.service.PlaceDetailsService;
import pl.coderslab.finalproject.service.PlaceService;

import java.util.List;

@Controller
@RequestMapping("/place-details")
@AllArgsConstructor
public class PlaceDetailsController {
    private final PlaceDetailsService placeDetailsService;
    private final PlaceService placeService;
    private final EventService eventService;

//    @GetMapping("/place-id/{placeId}")
//    public String getDetailsByPlaceId(@PathVariable Long placeId, Model model) {
//        model.addAttribute("placeDetails", placeDetailsService.getDetailsByPlaceId(placeId));
//        return "initial_place_details";
//    }
    @GetMapping("/place-id/{placeId}")
    public String getDetailsByPlaceId(@PathVariable Long placeId, Model model) {
        model.addAttribute("current_place", placeService.getByIdDTO(placeId).getBody());
        model.addAttribute("current_details", placeDetailsService.getDetailsByPlaceIdDTO(placeId).getBody());
        model.addAttribute("current_events", eventService.getAllEventsByPlaceId(placeId).getBody());
        return "initial_place_details";
    }

//    @PostMapping("/create/{placeId}")
//    public ResponseEntity<String> addDetails( @PathVariable Long placeId, @RequestBody PlaceDetailsDTO placeDetailsDTO) {
//        return placeDetailsService.addDetails(placeId, placeDetailsDTO);
//    }

    @GetMapping("/add/{placeId}")
    public String addDetails(@PathVariable Long placeId, Model model) {
        model.addAttribute("current_place", placeService.getByIdDTO(placeId).getBody());
        return "initial_add_details";
    }

    @PostMapping("/add/{placeId}")
    public String addDetails(@PathVariable Long placeId, HttpServletRequest request, Model model) {
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String activities = request.getParameter("activities");
        String description = request.getParameter("description");
        PlaceDetailsDTO placeDetailsDTO = new PlaceDetailsDTO();
        placeDetailsDTO.setCountry(country);
        placeDetailsDTO.setLocation(city);
        placeDetailsDTO.setAddress(address);
        placeDetailsDTO.setActivites(activities);
        placeDetailsDTO.setDescription(description);
        placeDetailsDTO.setPlaceId(placeId);
        ResponseEntity<String> addResponse = placeDetailsService.addDetails(placeId, placeDetailsDTO);
        if (addResponse.getStatusCode() == HttpStatus.CREATED) {
            HttpSession session = request.getSession();
            UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
            List<PlaceDTO> placeDTOS = placeService.getPlacesForUser(userDTO);
            session.setAttribute("userPlaces", placeDTOS);
            return "redirect:/place-details/place-id/" + placeId;
        } else {
            model.addAttribute("message", "Error");
            return "initial_add_details";
        }
    }


    @PutMapping("/update/{placeId}")
    public ResponseEntity<String> updateDetails(@PathVariable Long placeId, @RequestBody PlaceDetailsDTO placeDetailsDTO) {
        return placeDetailsService.updateDetails(placeId, placeDetailsDTO);
    }

    @DeleteMapping("/delete/{placeId}")
    public ResponseEntity<String> deleteDetails(@PathVariable Long placeId) {
        return placeDetailsService.deleteDetails(placeId);
    }

}
