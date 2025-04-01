package pl.coderslab.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.finalproject.entity.PlaceDetails;

import java.util.List;
import java.util.Optional;

public interface PlaceDetailsRepository extends JpaRepository<PlaceDetails, Long> {
    public Optional<PlaceDetails> findByPlaceId(Long id);
}
