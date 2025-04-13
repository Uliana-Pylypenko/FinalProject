package pl.coderslab.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.finalproject.entity.Event;


import java.util.List;


public interface EventRepository extends JpaRepository<Event, Long>, EventRepositoryCustom {
    List<Event> findAllByPlaceId(Long placeId);

    @Query("select e from Event e order by e.id desc limit 1")
    Event findByMaxId();

}
