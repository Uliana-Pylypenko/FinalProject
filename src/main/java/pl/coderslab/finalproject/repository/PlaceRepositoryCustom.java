package pl.coderslab.finalproject.repository;

import pl.coderslab.finalproject.entity.Category;
import pl.coderslab.finalproject.entity.Place;

import java.util.List;

public interface PlaceRepositoryCustom {
    List<Place> findByFilters(String name, String country, String location, String activity, List<Category> categories);
}
