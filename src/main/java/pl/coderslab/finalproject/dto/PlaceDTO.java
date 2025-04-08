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
    private PlaceDetailsDTO detailsDTO;
    private Long userId;
    private Long categoryId;
    private String latitude;
    private String longitude;
    private List<Long> eventIds; // = new ArrayList<>();

    public static PlaceDTO toDTO(Place place) {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setId(place.getId());
        placeDTO.setName(place.getName());
        placeDTO.setLatitude(String.valueOf(place.getLatitude()));
        placeDTO.setLongitude(String.valueOf(place.getLongitude()));
        //placeDTO.setDetailsId(place.getPlaceDetails().getId()); // can't be null for some reason
        PlaceDetails placeDetails = place.getPlaceDetails();
        placeDTO.setDetailsDTO(PlaceDetailsDTO.toDTO(placeDetails));
//        if (placeDetails != null) {
//            placeDTO.setDetailsId(placeDetails.getId());
//        } else {
//            placeDTO.setDetailsId(null); // Handle the case where PlaceDetails is null
//        }
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

//    public static Place toEntity(PlaceDTO placeDTO,
//                                 UserRepository userRepository,
//                                 PlaceDetailsRepository placeDetailsRepository,
//                                 CategoryRepository categoryRepository,
//                                 EventRepository eventRepository) {
//        Place place = new Place();
//        place.setId(placeDTO.getId());
//        place.setName(placeDTO.getName());
//        Long detailsId = placeDTO.getDetailsId();
//        if (detailsId != null) {
//            place.setPlaceDetails(placeDetailsRepository.findById(detailsId).orElse(null));
//        } else {
//            place.setPlaceDetails(null);
//        }
//        place.setUser(userRepository.findById(placeDTO.getUserId()).orElse(null));
//        place.setCategory(categoryRepository.findById(placeDTO.getCategoryId()).orElse(null));
//        List<Long> eventIds = placeDTO.getEventIds();
//        if (eventIds != null) {
//            place.setEvents(eventRepository.findAllById(placeDTO.getEventIds()));
//        } else {
//            place.setEvents(null);
//        }
//        return place;
//    }

    public static Place toEntityUserCategory(PlaceDTO placeDTO,
                                             UserRepository userRepository,
                                             CategoryRepository categoryRepository) {
        Place place = new Place();
        place.setId(placeDTO.getId());
        place.setName(placeDTO.getName());
        place.setLatitude(Double.parseDouble(placeDTO.getLatitude()));
        place.setLongitude(Double.parseDouble(placeDTO.getLongitude()));
        place.setUser(userRepository.findById(placeDTO.getUserId()).orElse(null));
        place.setCategory(categoryRepository.findById(placeDTO.getCategoryId()).orElse(null));
        return place;
    }

        public static Place toEntity(PlaceDTO placeDTO,
                                 UserRepository userRepository,
                                 PlaceRepository placeRepository,
                                 CategoryRepository categoryRepository,
                                 EventRepository eventRepository) {
            Place place = toEntityUserCategory(placeDTO, userRepository, categoryRepository);
            PlaceDetails placeDetails = PlaceDetailsDTO.toEntity(placeDTO.getDetailsDTO(), placeRepository);
            place.setPlaceDetails(placeDetails);
//            Long detailsId = placeDTO.getDetailsId();
//            if (detailsId != null) {
//                place.setPlaceDetails(placeDetailsRepository.findById(detailsId).orElse(null));
//            } else {
//                place.setPlaceDetails(null);
//            }
            List<Long> eventIds = placeDTO.getEventIds();
            if (eventIds != null) {
                place.setEvents(eventRepository.findAllById(placeDTO.getEventIds()));
            } else {
                place.setEvents(null);
            }
            return place;
        }
}
