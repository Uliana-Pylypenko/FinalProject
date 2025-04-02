package pl.coderslab.finalproject.dto;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.finalproject.entity.Event;
import pl.coderslab.finalproject.entity.Place;
import pl.coderslab.finalproject.repository.PlaceRepository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private Time time;
    private Long placeId;

    public static EventDTO toDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setDate(event.getDate());
        eventDTO.setTime(event.getTime());
        eventDTO.setPlaceId(event.getPlace().getId());
        return eventDTO;
    }

    public static Event toEntity(EventDTO eventDTO, PlaceRepository placeRepository) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setDate(eventDTO.getDate());
        event.setTime(eventDTO.getTime());
        Optional<Place> place = placeRepository.findById(eventDTO.getPlaceId());
        if (place.isPresent()) {
            event.setPlace(place.get());
        } else {
            throw new EntityNotFoundException("Place with id " + eventDTO.getPlaceId() + " not found");
        }
        return event;
    }
}
