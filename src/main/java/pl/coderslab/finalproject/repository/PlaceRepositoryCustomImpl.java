package pl.coderslab.finalproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import pl.coderslab.finalproject.entity.Place;
import java.util.function.Predicate;

import java.util.ArrayList;
import java.util.List;

public class PlaceRepositoryCustomImpl implements PlaceRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Place> findByFilters(String name, String country, String location) {
        StringBuilder queryBuilder = new StringBuilder("SELECT p FROM Place p WHERE 1=1");

        if (name != null && !name.isEmpty()) {
            queryBuilder.append(" AND p.name LIKE :name");
        }
        if (country != null && !country.isEmpty()) {
            queryBuilder.append(" AND p.placeDetails.country LIKE :country");
        }
        if (location != null && !location.isEmpty()) {
            queryBuilder.append(" AND p.placeDetails.location LIKE :location");
        }

        TypedQuery<Place> query = entityManager.createQuery(queryBuilder.toString(), Place.class);

        if (name != null && !name.isEmpty()) {
            query.setParameter("name", "%" + name + "%");
        }
        if (country != null && !country.isEmpty()) {
            query.setParameter("country", "%" + country + "%");
        }
        if (location != null && !location.isEmpty()) {
            query.setParameter("location", "%" + location + "%");
        }


        return query.getResultList();
    }
}
