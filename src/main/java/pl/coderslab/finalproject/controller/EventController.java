package pl.coderslab.finalproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.dto.EventDTO;
import pl.coderslab.finalproject.entity.Event;
import pl.coderslab.finalproject.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/event")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDTO>> getEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/place-id/{placeId}")
    public ResponseEntity<List<EventDTO>> getEventsByPlaceId(@PathVariable Long placeId) {
        return eventService.getAllEventsByPlaceId(placeId);
    }

    @PostMapping("/create/{placeId}")
    public ResponseEntity<String> createEvent(@PathVariable Long placeId, @RequestBody EventDTO eventDTO) {
        return eventService.addEvent(placeId, eventDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        return eventService.updateEvent(id, eventDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        return eventService.deleteEvent(id);
    }
}
