package pl.coderslab.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.finalproject.entity.Event;
import pl.coderslab.finalproject.entity.PlaceDetails;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    public List<Event> findAllByPlaceId(Long placeId);
}
