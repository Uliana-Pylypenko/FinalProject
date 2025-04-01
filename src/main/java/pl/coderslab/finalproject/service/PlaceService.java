package pl.coderslab.finalproject.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.dto.PlaceDTO;
import pl.coderslab.finalproject.entity.Place;
import pl.coderslab.finalproject.repository.DetailsRepository;
import pl.coderslab.finalproject.repository.PlaceRepository;
import pl.coderslab.finalproject.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final DetailsRepository detailsRepository;

    public ResponseEntity<List<PlaceDTO>> getAll() {
        List<PlaceDTO> placeDTOS = placeRepository
                .findAll()
                .stream()
                .map(PlaceDTO::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(placeDTOS, HttpStatus.OK);
    }

    public ResponseEntity<PlaceDTO> getById(Long id) {
        Optional<Place> place = placeRepository.findById(id);
        if (place.isPresent()) {
            PlaceDTO placeDTO = PlaceDTO.toDTO(place.get());
            return new ResponseEntity<>(placeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> create(PlaceDTO placeDTO) {
        placeRepository.save(PlaceDTO.toEntity(placeDTO, userRepository));
        return new ResponseEntity<>("Place successfully created", HttpStatus.CREATED);
    }





}
