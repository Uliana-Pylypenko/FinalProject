package pl.coderslab.finalproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="place_details")
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;
    private String location;
    private String address;
    private String description;
    private String activities;

    // it's better to have place_id in this table, bc first you add the place and then details
    // this way you don't have to provide details during creating the place
    @OneToOne
    Place place;



}
