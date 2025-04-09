package pl.coderslab.finalproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.dto.EventDTO;
import pl.coderslab.finalproject.dto.PlaceDTO;
import pl.coderslab.finalproject.repository.EventRepository;
import pl.coderslab.finalproject.service.EventService;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/event")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;
    private final EventRepository eventRepository;

//    @GetMapping
//    public String getEvents(Model model) {
//        model.addAttribute("events", eventService.getAll());
//        return "initial_events";
//    }

    @GetMapping
    public String getFilteredEvents(Model model, HttpServletRequest request) {
        model.addAttribute("events", eventService.getAllEventsDTO().getBody());
        String title = request.getParameter("title");
        LocalDate startDate = eventService.getDateFromString(request.getParameter("start_date"));
        LocalDate endDate = eventService.getDateFromString(request.getParameter("end_date"));

        model.addAttribute("filter_start_date", startDate);
        model.addAttribute("filter_end_date", endDate);

        List<EventDTO> filteredEvents = eventRepository
                .findByFilters(title, startDate, endDate)
                .stream()
                .map(EventDTO::toDTO)
                .collect(Collectors.toList());

        model.addAttribute("events", filteredEvents);
        return "initial_events";
    }

    @GetMapping("/{id}")
    public String getEvent(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventService.getById(id));
        return "initial_event_details";
    }


    @GetMapping("/place-id/{placeId}")
    public ResponseEntity<List<EventDTO>> getEventsByPlaceId(@PathVariable Long placeId) {
        return eventService.getAllEventsByPlaceId(placeId);
    }

    @GetMapping("/create")
    public String createEvent() {
        return "initial_add_event";
    }

    @PostMapping("/create")
    public String createEvent(HttpServletRequest request) {
        Long placeId = Long.parseLong(request.getParameter("place"));
        String title = request.getParameter("title");
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Time time = null;
        try {
            long ms = sdf.parse(request.getParameter("time")).getTime();
            time = new Time(ms);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String description = request.getParameter("description");

        EventDTO eventDTO = new EventDTO();
        eventDTO.setTitle(title);
        eventDTO.setDate(date);
        eventDTO.setTime(time);
        eventDTO.setDescription(description);

        HttpSession session = request.getSession();

        ResponseEntity<String> createResponse = eventService.addEvent(placeId, eventDTO);
        if (createResponse.getStatusCode() == HttpStatus.CREATED) {
            List<PlaceDTO> placeDTOS = (List<PlaceDTO>) session.getAttribute("userPlaces");
            if (placeDTOS == null) {
                placeDTOS = new ArrayList<>();
            } else {
                for (PlaceDTO placeDTO : placeDTOS) {
                    if (placeDTO.getId() == placeId) {
                        List<EventDTO> eventDTOS = placeDTO.getEventDTOS();
                        eventDTOS.add(eventDTO);
                        placeDTO.setEventDTOS(eventDTOS);
                    }
                }
            }
            session.setAttribute("userPlaces", placeDTOS);
            return "redirect:/user/home";
        } else {
            return "error";
        }


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
