package pl.coderslab.finalproject.repository;

import pl.coderslab.finalproject.entity.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventRepositoryCustom {
    List<Event> findByFilters(LocalDate startDate, LocalDate endDate, String country, String city);
}
