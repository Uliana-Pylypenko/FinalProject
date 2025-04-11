package pl.coderslab.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.finalproject.entity.Event;
import pl.coderslab.finalproject.entity.PlaceDetails;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long>, EventRepositoryCustom {
    List<Event> findAllByPlaceId(Long placeId);

    @Query("select e from Event e order by e.id desc limit 1")
    Event findByMaxId();

//    @Query("select e from Event e where e.date >= local_date")
//    List<Event> findFutureEvents();
//

}
