package pl.coderslab.finalproject.service;

import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.dto.CategoryDTO;
import pl.coderslab.finalproject.dto.PlaceDTO;
import pl.coderslab.finalproject.dto.UserDTO;
import pl.coderslab.finalproject.entity.Category;
import pl.coderslab.finalproject.entity.Place;
import pl.coderslab.finalproject.entity.User;
import pl.coderslab.finalproject.exception.DuplicatePlaceNameException;
import pl.coderslab.finalproject.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    public ResponseEntity<List<PlaceDTO>> getAllDTO() {
        List<PlaceDTO> placeDTOS = placeRepository
                .findAll()
                .stream()
                .map(PlaceDTO::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(placeDTOS, HttpStatus.OK);
    }

    public ResponseEntity<PlaceDTO> getByIdDTO(Long id) {
        Optional<Place> place = placeRepository.findById(id);
        if (place.isPresent()) {
            PlaceDTO placeDTO = PlaceDTO.toDTO(place.get());
            return new ResponseEntity<>(placeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<PlaceDTO>> getFilteredPlaces(String name,
                                                            String country,
                                                            String city,
                                                            String activity,
                                                            List<CategoryDTO> checkedCategories) {
        List<PlaceDTO> filteredPlaces = placeRepository
                .findByFilters(name, country, city, activity, checkedCategories.stream().map(CategoryDTO::toEntity).collect(Collectors.toList()))
                .stream()
                .map(PlaceDTO::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(filteredPlaces, HttpStatus.OK);
    }

    // creates Place with PlaceDetails=null
    public ResponseEntity<String> create(PlaceDTO placeDTO) {
        checkForDuplicate(placeDTO);
        Long userId = placeDTO.getUserId();
        Optional<User> user = userRepository.findById(userId);
        Optional<Category> category = categoryRepository.findById(placeDTO.getCategoryDTO().getId());
        if (user.isPresent() && category.isPresent()) {
            // when we create a place, we don't have neither details nor events yet
            boolean approved = user.get().isAdmin();
            placeDTO.setApproved(approved);
            placeRepository.save(PlaceDTO.toEntityUserCategory(placeDTO, userRepository));
            return new ResponseEntity<>("Place successfully created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("User or category not found", HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<String> update(Long place_id, PlaceDTO placeDTO) {
        checkForDuplicate(placeDTO);
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

    public List<PlaceDTO> getPlacesForUser(UserDTO userDTO) {
        List<PlaceDTO> placeDTOS = new ArrayList<>();
        if (userDTO.getPlaceIds() != null) {
            placeDTOS = placeRepository.findAllById(userDTO.getPlaceIds())
                    .stream()
                    .map(PlaceDTO::toDTO)
                    .collect(Collectors.toList());
        }
        return placeDTOS;
    }

    public PlaceDTO getLastElement() {
        Place place = placeRepository.findByMaxIndex();
        return PlaceDTO.toDTO(place);
    }

    public List<PlaceDTO> getNotApprovedPlaces() {
        List<PlaceDTO> placeDTOS = placeRepository
                .findNotApproved()
                .stream()
                .map(PlaceDTO::toDTO)
                .collect(Collectors.toList());
        return placeDTOS;
    }

    public ResponseEntity<String> approvePlace(Long id) {
        Optional<Place> place = placeRepository.findById(id);
        if (place.isPresent()) {
            place.get().setApproved(true);
            placeRepository.save(place.get());
            return new ResponseEntity<>("Place successfully approved", HttpStatus.OK);
        }
        return new ResponseEntity<>("Place not found", HttpStatus.NOT_FOUND);
    }

    public void checkForDuplicate(PlaceDTO placeDTO) {
        Optional<Place> place = placeRepository.findByName(placeDTO.getName());
        if (place.isPresent() && place.get().getId() != placeDTO.getId()) {
            throw new DuplicatePlaceNameException("Place with name " + placeDTO.getName() + " already exists");
        }
    }

    public static JSONObject singlePlaceToJSON(PlaceDTO placeDTO) {
        if (placeDTO == null) {
            return null;
        } else {
            JSONObject placeJson = new JSONObject();
            placeJson.put("latitude", placeDTO.getLatitude());
            placeJson.put("longitude", placeDTO.getLongitude());
            placeJson.put("name", placeDTO.getName());
            if (placeDTO.getDetailsDTO() != null) {
                placeJson.put("city", placeDTO.getDetailsDTO().getLocation());
                placeJson.put("address", placeDTO.getDetailsDTO().getAddress());
                placeJson.put("country", placeDTO.getDetailsDTO().getCountry());
            } else {
                placeJson.put("city", "none");
                placeJson.put("address", "none");
                placeJson.put("country", "none");
            }
            placeJson.put("category", placeDTO.getCategoryDTO().getName());
            return placeJson;
        }
    }

    public static JSONArray placesToJSON(List<PlaceDTO> placeDTOS) {
        if (placeDTOS == null) {
            return null;
        } else {
            JSONArray jsonArray = new JSONArray();
            for (PlaceDTO placeDTO : placeDTOS) {
                JSONObject placeJson = singlePlaceToJSON(placeDTO);
                jsonArray.put(placeJson);
            }
            return jsonArray;
        }

    }

}
