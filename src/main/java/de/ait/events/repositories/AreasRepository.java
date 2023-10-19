package de.ait.events.repositories;

import de.ait.events.models.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreasRepository extends JpaRepository<Area, Long> {
}
