package pl.coderslab.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.finalproject.entity.Event;
import pl.coderslab.finalproject.entity.Place;
import pl.coderslab.finalproject.entity.PlaceDetails;
import pl.coderslab.finalproject.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDTO {
    private Long id;
    private String name;
    private Long userId;
    private CategoryDTO categoryDTO;
    private double latitude;
    private double longitude;
    private boolean isApproved;
    private PlaceDetailsDTO detailsDTO;
    private List<EventDTO> eventDTOS; // = new ArrayList<>();

    public static PlaceDTO toDTO(Place place) {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setId(place.getId());
        placeDTO.setName(place.getName());
        placeDTO.setLatitude(place.getLatitude());
        placeDTO.setLongitude(place.getLongitude());
        placeDTO.setApproved(place.isApproved());
        PlaceDetails placeDetails = place.getPlaceDetails();
        if (placeDetails != null) {
            placeDTO.setDetailsDTO(PlaceDetailsDTO.toDTO(placeDetails));
        } else {
            placeDTO.setDetailsDTO(null);
        }
        placeDTO.setUserId(place.getUser().getId());
        placeDTO.setCategoryDTO(CategoryDTO.toDTO(place.getCategory()));

        List<Event> events = place.getEvents();
        if (events != null) {
            List<EventDTO> eventDTOS = events
                    .stream()
                    .map(EventDTO::toDTO)
                    .collect(Collectors.toList());
            placeDTO.setEventDTOS(eventDTOS);
        } else {
            placeDTO.setEventDTOS(null);
        }
        return placeDTO;
    }

    public static Place toEntityUserCategory(PlaceDTO placeDTO,
                                             UserRepository userRepository) {
        Place place = new Place();
        place.setId(placeDTO.getId());
        place.setName(placeDTO.getName());
        place.setLatitude(placeDTO.getLatitude());
        place.setLongitude(placeDTO.getLongitude());
        place.setApproved(placeDTO.isApproved());
        place.setUser(userRepository.findById(placeDTO.getUserId()).orElse(null));
        place.setCategory(CategoryDTO.toEntity(placeDTO.getCategoryDTO()));
        return place;
    }

        public static Place toEntity(PlaceDTO placeDTO,
                                 UserRepository userRepository,
                                 PlaceRepository placeRepository,
                                 CategoryRepository categoryRepository) {
            Place place = toEntityUserCategory(placeDTO, userRepository);
            PlaceDetails placeDetails = PlaceDetailsDTO.toEntity(placeDTO.getDetailsDTO(), placeRepository);
            place.setPlaceDetails(placeDetails);
            List<Event> events = placeDTO
                    .getEventDTOS()
                    .stream()
                    .map(eventDTO -> EventDTO.toEntity(eventDTO, placeRepository))
                    .collect(Collectors.toList());
            place.setEvents(events);
            return place;
        }
}
