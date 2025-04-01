package pl.coderslab.finalproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.dto.PlaceDTO;
import pl.coderslab.finalproject.entity.Category;
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

    @GetMapping("/{id}")
    public ResponseEntity<PlaceDTO> getById(@PathVariable Long id) {
        return placeService.getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody PlaceDTO placeDTO) {
        return placeService.create(placeDTO);
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
