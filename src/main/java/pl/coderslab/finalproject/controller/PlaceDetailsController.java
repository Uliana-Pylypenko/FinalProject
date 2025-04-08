package pl.coderslab.finalproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.dto.PlaceDetailsDTO;
import pl.coderslab.finalproject.service.EventService;
import pl.coderslab.finalproject.service.PlaceDetailsService;
import pl.coderslab.finalproject.service.PlaceService;

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

    @PostMapping("/create/{placeId}")
    public ResponseEntity<String> addDetails( @PathVariable Long placeId, @RequestBody PlaceDetailsDTO placeDetailsDTO) {
        return placeDetailsService.addDetails(placeId, placeDetailsDTO);
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
