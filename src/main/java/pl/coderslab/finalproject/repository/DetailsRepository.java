package pl.coderslab.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.finalproject.entity.PlaceDetails;

public interface DetailsRepository extends JpaRepository<PlaceDetails, Long> {
}
