package de.ait.events.repositories;

import de.ait.events.models.Area;
import de.ait.events.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface EventsRepository extends JpaRepository<Event, Long> {
    Set<Event> findAllByAreaOrderById(Area area);

    Optional<Event> findByAreaAndId(Area area, Long eventId);
}
