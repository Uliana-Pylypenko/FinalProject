package pl.coderslab.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.finalproject.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
