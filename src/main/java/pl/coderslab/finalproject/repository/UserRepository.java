package pl.coderslab.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.finalproject.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
