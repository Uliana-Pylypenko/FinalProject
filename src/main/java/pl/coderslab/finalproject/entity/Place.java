package pl.coderslab.finalproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="places")
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToMany
//    @JoinTable(name = "place_category",
//            joinColumns = @JoinColumn(name = "place_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id"))
//    private Set<Category> categories;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(mappedBy = "place", cascade = CascadeType.ALL)
    private PlaceDetails placeDetails;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();

    private double longitude;
    private double latitude;

    private boolean isApproved;



}
