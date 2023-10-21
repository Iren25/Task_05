package de.ait.events.repositories;

import de.ait.events.models.Event;
import de.ait.events.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UsersRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Set<User> findAllByEventsContainsOrderById(Event event);
}
