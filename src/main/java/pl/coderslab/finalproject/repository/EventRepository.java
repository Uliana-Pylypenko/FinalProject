package pl.coderslab.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.finalproject.entity.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    public List<Event> findAllByPlaceId(Long placeId);
}
