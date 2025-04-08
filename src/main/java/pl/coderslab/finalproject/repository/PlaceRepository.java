package pl.coderslab.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.finalproject.dto.PlaceDTO;
import pl.coderslab.finalproject.entity.Category;
import pl.coderslab.finalproject.entity.Place;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceRepositoryCustom {
    @Query("select p from Place p where p.name like %?1%")
    List<Place> filterByPlaceName(String placeName);

    @Query("select p from Place p where p.placeDetails.country like %?1%")
    List<Place> filterByCountry(String country);

    @Query("select p from Place p where p.placeDetails.location like %?1%")
    List<Place> filterByLocation(String location);



}
