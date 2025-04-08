package pl.coderslab.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.finalproject.entity.Place;
import pl.coderslab.finalproject.entity.PlaceDetails;
import pl.coderslab.finalproject.repository.PlaceRepository;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDetailsDTO {
    private Long id;
    private String country;
    private String location;
    private String address;
    private String description;
    private String activites;
    private Long placeId;

   public static PlaceDetailsDTO toDTO(PlaceDetails placeDetails) {
       PlaceDetailsDTO placeDetailsDTO = new PlaceDetailsDTO();
       placeDetailsDTO.setId(placeDetails.getId());
       placeDetailsDTO.setCountry(placeDetails.getCountry());
       placeDetailsDTO.setLocation(placeDetails.getLocation());
       placeDetailsDTO.setAddress(placeDetails.getAddress());
       placeDetailsDTO.setDescription(placeDetails.getDescription());
       placeDetailsDTO.setActivites(placeDetails.getActivites());
       placeDetailsDTO.setPlaceId(placeDetails.getPlace().getId());
       return placeDetailsDTO;
   }

   public static PlaceDetails toEntity(PlaceDetailsDTO placeDetailsDTO, PlaceRepository placeRepository) {
       PlaceDetails placeDetails = new PlaceDetails();
       placeDetails.setId(placeDetailsDTO.getId());
       placeDetails.setCountry(placeDetailsDTO.getCountry());
       placeDetails.setLocation(placeDetailsDTO.getLocation());
       placeDetails.setAddress(placeDetailsDTO.getAddress());
       placeDetails.setDescription(placeDetailsDTO.getDescription());
       placeDetails.setActivites(placeDetailsDTO.getActivites());
       placeDetails.setPlace(placeRepository.findById(placeDetailsDTO.getPlaceId()).orElse(null));
       return placeDetails;
   }
}
