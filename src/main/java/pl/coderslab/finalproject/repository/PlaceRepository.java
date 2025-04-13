package pl.coderslab.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.finalproject.entity.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceRepositoryCustom {
    @Query("select p from Place p order by p.id desc limit 1")
    Place findByMaxIndex();

    @Query("select p from Place p where p.isApproved = false")
    List<Place> findNotApproved();

    Optional<Place> findByName(String name);



}
