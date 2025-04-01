package pl.coderslab.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.finalproject.entity.Category;
import pl.coderslab.finalproject.entity.Place;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
