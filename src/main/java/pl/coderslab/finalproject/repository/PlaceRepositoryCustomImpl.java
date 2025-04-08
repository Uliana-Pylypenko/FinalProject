package pl.coderslab.finalproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import pl.coderslab.finalproject.dto.CategoryDTO;
import pl.coderslab.finalproject.entity.Category;
import pl.coderslab.finalproject.entity.Place;

import java.util.Map;
import java.util.function.Predicate;

import java.util.ArrayList;
import java.util.List;

public class PlaceRepositoryCustomImpl implements PlaceRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Place> findByFilters(String name,
                                     String country,
                                     String location,
                                     String activity,
                                     List<Category> categories) {
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
        if (activity != null && !activity.isEmpty()) {
            queryBuilder.append(" AND p.placeDetails.activities LIKE :activity");
        }

        if (!categories.isEmpty()) {
            queryBuilder.append(" AND (");
            for (int i = 0; i < categories.size(); i++) {
                if (i > 0) {
                    queryBuilder.append(" OR ");
                }
                queryBuilder.append("p.category = :category" + i);
            }
            queryBuilder.append(")");
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
        if (activity != null && !activity.isEmpty()) {
            query.setParameter("activity", "%" + activity + "%");
        }
        for (int i = 0; i < categories.size(); i++) {
            query.setParameter("category" + i, categories.get(i));
        }

        return query.getResultList();
    }
}
