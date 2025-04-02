package pl.coderslab.finalproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.dto.PlaceDetailsDTO;
import pl.coderslab.finalproject.service.PlaceDetailsService;

@RestController
@RequestMapping("/place-details")
@AllArgsConstructor
public class PlaceDetailsController {
    private final PlaceDetailsService placeDetailsService;

    @GetMapping("/place-id/{placeId}")
    public ResponseEntity<PlaceDetailsDTO> getDetailsByPlaceId(@PathVariable Long placeId) {
        return placeDetailsService.getDetailsByPlaceId(placeId);
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
