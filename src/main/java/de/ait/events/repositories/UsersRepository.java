package de.ait.events.repositories;

import de.ait.events.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
