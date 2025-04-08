package pl.coderslab.finalproject.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.dto.PlaceDTO;
import pl.coderslab.finalproject.entity.Category;
import pl.coderslab.finalproject.entity.Place;
import pl.coderslab.finalproject.entity.User;
import pl.coderslab.finalproject.repository.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<Place> getAll() {
        return placeRepository.findAll();
    }

    public ResponseEntity<List<PlaceDTO>> getAllDTO() {
        List<PlaceDTO> placeDTOS = placeRepository
                .findAll()
                .stream()
                .map(PlaceDTO::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(placeDTOS, HttpStatus.OK);
    }

    public Place getById(Long id) {
        return placeRepository.findById(id).orElse(null);
    }

    public ResponseEntity<PlaceDTO> getByIdDTO(Long id) {
        Optional<Place> place = placeRepository.findById(id);
        if (place.isPresent()) {
            PlaceDTO placeDTO = PlaceDTO.toDTO(place.get());
            return new ResponseEntity<>(placeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // creates Place with PlaceDetails=null
    public ResponseEntity<String> create(PlaceDTO placeDTO) {
        Long userId = placeDTO.getUserId();
        Optional<User> user = userRepository.findById(userId);
        Optional<Category> category = categoryRepository.findById(placeDTO.getCategoryDTO().getId());
        if (user.isPresent() && category.isPresent()) {
            // when we create a place, we don't have neither details nor events yet
            placeRepository.save(PlaceDTO.toEntityUserCategory(placeDTO, userRepository));
            return new ResponseEntity<>("Place successfully created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("User or category not found", HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<String> update(Long place_id, PlaceDTO placeDTO) {
        Optional<Place> place = placeRepository.findById(place_id);
        // userId can't be changed
        if (place.isPresent()) {
            Long categoryId = placeDTO.getCategoryDTO().getId();
            Optional<Category> category = categoryRepository.findById(categoryId);
            if (category.isPresent()) {
                place.get().setName(placeDTO.getName());
                place.get().setCategory(category.get());
                placeRepository.save(place.get());
                return new ResponseEntity<>("Place successfully updated", HttpStatus.OK);
            }
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Place not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> delete(Long place_id) {
        Optional<Place> place = placeRepository.findById(place_id);
        if (place.isPresent()) {
            placeRepository.delete(place.get());
            return new ResponseEntity<>("Place successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Place not found", HttpStatus.NOT_FOUND);
    }




}
