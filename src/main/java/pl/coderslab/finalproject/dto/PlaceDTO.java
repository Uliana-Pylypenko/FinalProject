package pl.coderslab.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.finalproject.entity.Event;
import pl.coderslab.finalproject.entity.Place;
import pl.coderslab.finalproject.entity.PlaceDetails;
import pl.coderslab.finalproject.repository.CategoryRepository;
import pl.coderslab.finalproject.repository.EventRepository;
import pl.coderslab.finalproject.repository.PlaceDetailsRepository;
import pl.coderslab.finalproject.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDTO {
    private Long id;
    private String name;
    private Long detailsId;
    private Long userId;
    private Long categoryId;
    private List<Long> eventIds;

    public static PlaceDTO toDTO(Place place) {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setId(place.getId());
        placeDTO.setName(place.getName());
        //placeDTO.setDetailsId(place.getPlaceDetails().getId());// can't be null for some reason
        PlaceDetails placeDetails = place.getPlaceDetails();
        if (placeDetails != null) {
            placeDTO.setDetailsId(placeDetails.getId());
        } else {
            placeDTO.setDetailsId(null); // Handle the case where PlaceDetails is null
        }
        placeDTO.setUserId(place.getUser().getId());
        placeDTO.setCategoryId(place.getCategory().getId());
        List<Long> eventIds = place
                .getEvents()
                .stream()
                .map(Event::getId)
                .collect(Collectors.toList());
        placeDTO.setEventIds(eventIds);
        return placeDTO;
    }

    public static Place toEntity(PlaceDTO placeDTO,
                                 UserRepository userRepository,
                                 PlaceDetailsRepository placeDetailsRepository,
                                 CategoryRepository categoryRepository,
                                 EventRepository eventRepository) {
        Place place = new Place();
        place.setId(placeDTO.getId());
        place.setName(placeDTO.getName());
        place.setPlaceDetails(placeDetailsRepository.findById(placeDTO.getDetailsId()).orElse(null));
        place.setUser(userRepository.findById(placeDTO.getUserId()).orElse(null));
        place.setCategory(categoryRepository.findById(placeDTO.getCategoryId()).orElse(null));
        place.setEvents(eventRepository.findAllById(placeDTO.getEventIds()));
        return place;
    }
}
