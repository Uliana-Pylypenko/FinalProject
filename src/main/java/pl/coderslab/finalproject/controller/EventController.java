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
import pl.coderslab.finalproject.dto.PlaceDetailsDTO;
import pl.coderslab.finalproject.entity.Place;
import pl.coderslab.finalproject.repository.EventRepository;
import pl.coderslab.finalproject.service.EventService;
import pl.coderslab.finalproject.service.PlaceService;

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
    private final PlaceService placeService;

//    @GetMapping
//    public String getEvents(Model model) {
//        model.addAttribute("events", eventService.getAll());
//        return "initial_events";
//    }

    @GetMapping
    public String getFilteredEvents(Model model, HttpServletRequest request) {
        model.addAttribute("events", eventService.getAllEventsDTO().getBody());
        LocalDate startDate = eventService.getDateFromString(request.getParameter("start_date"));
        LocalDate endDate = eventService.getDateFromString(request.getParameter("end_date"));
        String country = request.getParameter("country");
        String city = request.getParameter("city");

        model.addAttribute("filter_start_date", startDate);
        model.addAttribute("filter_end_date", endDate);
        model.addAttribute("filter_country", country);
        model.addAttribute("filter_city", city);

        List<EventDTO> filteredEvents = eventService.getFilteredEvents(startDate, endDate, country, city).getBody();

        model.addAttribute("events", filteredEvents);
        return "events";
    }

    @GetMapping("/{id}")
    public String getEvent(@PathVariable Long id, Model model) {
        model.addAttribute("current_single_event", eventService.getByIdDTO(id));
        Place place = eventService.getById(id).getPlace();
        model.addAttribute("current_place", PlaceDTO.toDTO(place));
        PlaceDetailsDTO current_details = place.getPlaceDetails() == null? null : PlaceDetailsDTO.toDTO(place.getPlaceDetails());
        model.addAttribute("current_details", current_details);
        model.addAttribute("current_events", eventService.getAllEventsByPlaceId(place.getId()).getBody());
        return "event_details";
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
        EventDTO eventDTO = eventDTOForm(request);
        Long placeId = eventDTO.getPlaceId();

        HttpSession session = request.getSession();

        ResponseEntity<String> createResponse = eventService.addEvent(placeId, eventDTO);

        //this can be simplified
        if (createResponse.getStatusCode() == HttpStatus.CREATED) {
            List<PlaceDTO> placeDTOS = (List<PlaceDTO>) session.getAttribute("userPlaces");
            if (placeDTOS == null) {
                placeDTOS = new ArrayList<>();
            } else {
                for (PlaceDTO placeDTO : placeDTOS) {
                    if (placeDTO.getId() == placeId) {
                        List<EventDTO> eventDTOS = placeDTO.getEventDTOS();
                        eventDTOS.add(eventService.getLastElement());
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

//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
//        return eventService.updateEvent(id, eventDTO);
//    }

    @GetMapping("/update/{id}")
    public String updateEvent(@PathVariable Long id, Model model) {
        model.addAttribute("current_single_event", eventService.getByIdDTO(id));
        return "initial_add_event";
    }

    @PostMapping("/update/{id}")
    public String updateEvent(@PathVariable Long id, HttpServletRequest request, Model model) {
        EventDTO eventDTO = eventDTOForm(request);
        Long placeId = eventDTO.getPlaceId();
        if (placeId != null) {
            eventService.updateEvent(id, eventDTO);
            return "redirect:/event/" + id;
        } else {
            model.addAttribute("error_message", "Choose a place");
            model.addAttribute("event", eventService.getByIdDTO(id));
            return "initial_add_event";
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        return eventService.deleteEvent(id);
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id, Model model) {
        String message = "Are you sure you want to delete this event?";
        model.addAttribute("delete_message", message);
        return "initial_delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id, HttpServletRequest request, Model model) {
        boolean answer = Boolean.parseBoolean(request.getParameter("answer"));
        if (answer) {
            Long placeId = eventService.getByIdDTO(id).getPlaceId();
            eventService.deleteEvent(id);

            HttpSession session = request.getSession();
            List<PlaceDTO> placeDTOS = (List<PlaceDTO>) session.getAttribute("userPlaces");
            for (PlaceDTO placeDTO : placeDTOS) {
                if (placeDTO.getId() == placeId) {
                    placeDTO.setEventDTOS(eventService.getAllEventsByPlaceId(placeId).getBody());
                }
            }
            session.setAttribute("userPlaces", placeDTOS);
            return "redirect:/user/home";
        } else {
            return "redirect:/event/" + id;
        }
    }

    public EventDTO eventDTOForm(HttpServletRequest request) {
        String placeIdString = request.getParameter("place");
        Long placeId = placeIdString != null ? Long.parseLong(placeIdString) : null;
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
        eventDTO.setPlaceId(placeId);
        return eventDTO;
    }
}
