package pl.coderslab.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.finalproject.entity.PlaceDetails;

import java.util.Optional;

public interface PlaceDetailsRepository extends JpaRepository<PlaceDetails, Long> {
    @Query("select p from PlaceDetails p where p.place.id = ?1")
    public Optional<PlaceDetails> findByPlaceId(Long id);

}
