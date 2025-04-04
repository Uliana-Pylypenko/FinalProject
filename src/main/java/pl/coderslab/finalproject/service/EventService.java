package pl.coderslab.finalproject.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.dto.EventDTO;
import pl.coderslab.finalproject.entity.Event;
import pl.coderslab.finalproject.entity.Place;
import pl.coderslab.finalproject.entity.PlaceDetails;
import pl.coderslab.finalproject.repository.EventRepository;
import pl.coderslab.finalproject.repository.PlaceRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public Event getById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public ResponseEntity<List<EventDTO>> getAllEventsDTO() {
        List<EventDTO> eventDTOS = eventRepository
                .findAll()
                .stream()
                .map(EventDTO::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(eventDTOS, HttpStatus.OK);
    }

    public ResponseEntity<List<EventDTO>> getAllEventsByPlaceId(Long placeId) {
        List<EventDTO> eventDTOS = eventRepository
                .findAllByPlaceId(placeId)
                .stream()
                .map(EventDTO::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(eventDTOS, HttpStatus.OK);
    }

    // get event by location
    // get event by date, date range

    public ResponseEntity<String> addEvent(Long placeId, EventDTO eventDTO) {
        Optional<Place> place = placeRepository.findById(placeId);
        if (place.isPresent()) {
            eventDTO.setPlaceId(placeId);
            Event event = EventDTO.toEntity(eventDTO, placeRepository);
            eventRepository.save(event);
            return new ResponseEntity<>("Event added", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Place not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updateEvent(Long id, EventDTO eventDTO) {
        Optional<Event> eventToUpdate = eventRepository.findById(id);
        if (eventToUpdate.isPresent()) {
            Event event = eventToUpdate.get();
            event.setDescription(eventDTO.getDescription());
            event.setDate(eventDTO.getDate());
            event.setTime(eventDTO.getTime());
            event.setTitle(eventDTO.getTitle());
            // place can't be changed
            eventRepository.save(event);
            return new ResponseEntity<>("Event updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteEvent(Long id) {
        Optional<Event> eventToDelete = eventRepository.findById(id);
        if (eventToDelete.isPresent()) {
            eventRepository.deleteById(id);
            return new ResponseEntity<>("Event deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
    }
}
