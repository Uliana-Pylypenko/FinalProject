package pl.coderslab.finalproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import pl.coderslab.finalproject.entity.Event;
import pl.coderslab.finalproject.entity.Place;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class EventRepositoryCustomImpl implements EventRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Event> findByFilters(String title, LocalDate startDate, LocalDate endDate) {
        StringBuilder queryBuilder = new StringBuilder("SELECT e FROM Event e WHERE 1=1");

        if (title != null && !title.isEmpty()) {
            queryBuilder.append(" AND e.title LIKE :title");
        }
        if (startDate != null) {
            queryBuilder.append(" AND e.date >= :startDate");
        }
        if (endDate != null) {
            queryBuilder.append(" AND e.date <= :endDate");
        }

        TypedQuery<Event> query = entityManager.createQuery(queryBuilder.toString(), Event.class);
        if (title != null && !title.isEmpty()) {
            query.setParameter("title", "%" + title + "%");
        }
        if (startDate != null) {
            query.setParameter("startDate", startDate);
        }
        if (endDate != null) {
            query.setParameter("endDate", endDate);
        }
        return query.getResultList();
    }

}
