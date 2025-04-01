package pl.coderslab.finalproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.finalproject.dto.PlaceDTO;
import pl.coderslab.finalproject.entity.Place;
import pl.coderslab.finalproject.service.PlaceService;

import java.util.List;

@RestController
@RequestMapping("/place")
@AllArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping
    public ResponseEntity<List<PlaceDTO>> getAll() {
        return placeService.getAll();
    }
}
