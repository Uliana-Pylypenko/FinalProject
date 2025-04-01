package pl.coderslab.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.finalproject.entity.Place;
import pl.coderslab.finalproject.repository.DetailsRepository;
import pl.coderslab.finalproject.repository.UserRepository;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDTO {
    private Long id;
    private String name;
    //private Long detailsId;
    private Long userId;
    private Set<Long> categoryIds;

    public static PlaceDTO toDTO(Place place) {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setId(place.getId());
        placeDTO.setName(place.getName());
        //placeDTO.setDetailsId(place.getDetails().getId());
        placeDTO.setUserId(place.getUser().getId());
        return placeDTO;
    }

    public static Place toEntity(PlaceDTO placeDTO, UserRepository userRepository) {
        Place place = new Place();
        place.setId(placeDTO.getId());
        place.setName(placeDTO.getName());
        //place.setDetails(detailsRepository.findById(placeDTO.getDetailsId()).orElse(null));
        place.setUser(userRepository.findById(placeDTO.getUserId()).orElse(null));
        return place;
    }
}
