package pl.coderslab.finalproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.dto.PlaceDetailsDTO;
import pl.coderslab.finalproject.service.PlaceDetailsService;

@Controller
@RequestMapping("/place-details")
@AllArgsConstructor
public class PlaceDetailsController {
    private final PlaceDetailsService placeDetailsService;

    @GetMapping("/place-id/{placeId}")
    public String getDetailsByPlaceId(@PathVariable Long placeId, Model model) {
        model.addAttribute("placeDetails", placeDetailsService.getDetailsByPlaceId(placeId));
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
