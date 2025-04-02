package pl.coderslab.finalproject.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.dto.PlaceDetailsDTO;
import pl.coderslab.finalproject.entity.Place;
import pl.coderslab.finalproject.entity.PlaceDetails;
import pl.coderslab.finalproject.repository.PlaceDetailsRepository;
import pl.coderslab.finalproject.repository.PlaceRepository;

import java.util.Optional;
import java.util.OptionalInt;

@Service
@AllArgsConstructor
public class PlaceDetailsService {
    private final PlaceRepository placeRepository;
    private final PlaceDetailsRepository placeDetailsRepository;

    public ResponseEntity<PlaceDetailsDTO> getDetailsByPlaceId(Long placeId) {
        Optional<PlaceDetails> placeDetails = placeDetailsRepository.findByPlaceId(placeId);
        if (placeDetails.isPresent()) {
            PlaceDetailsDTO placeDetailsDTO = PlaceDetailsDTO.toDTO(placeDetails.get());
            return new ResponseEntity<>(placeDetailsDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> addDetails(Long placeId, PlaceDetailsDTO placeDetailsDTO) {
        Optional<Place> place = placeRepository.findById(placeId);
        if (place.isPresent()) {
            placeDetailsDTO.setPlaceId(placeId);
            placeDetailsRepository.save(PlaceDetailsDTO.toEntity(placeDetailsDTO, placeRepository));
            return new ResponseEntity<>("Details added successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Place not found", HttpStatus.NOT_FOUND);
        // add some message for duplicate entry
    }

    public ResponseEntity<String> updateDetails(Long placeId, PlaceDetailsDTO placeDetailsDTO) {
        Optional<PlaceDetails> placeDetailsToUpdate = placeDetailsRepository.findByPlaceId(placeId);
        if (placeDetailsToUpdate.isPresent()) {
            PlaceDetails placeDetails = placeDetailsToUpdate.get();
            placeDetails.setCountry(placeDetailsDTO.getCountry());
            placeDetails.setAddress(placeDetailsDTO.getAddress());
            placeDetails.setLocation(placeDetailsDTO.getLocation());
            placeDetails.setDescription(placeDetailsDTO.getDescription());
            placeDetails.setActivites(placeDetailsDTO.getActivites());
            placeDetailsRepository.save(placeDetails);
            return new ResponseEntity<>("Details updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Place not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteDetails(Long placeId) {
        Optional<PlaceDetails> placeDetails = placeDetailsRepository.findByPlaceId(placeId);
        Optional<Place> place = placeRepository.findById(placeId);
        if (placeDetails.isPresent()) {
            place.get().setPlaceDetails(null); // !!!
            placeDetailsRepository.delete(placeDetails.get());
            return new ResponseEntity<>("Details deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Place not found", HttpStatus.NOT_FOUND);
    }

}
