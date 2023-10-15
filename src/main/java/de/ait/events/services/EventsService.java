package de.ait.events.services;

import de.ait.events.dto.EventDto;
import de.ait.events.dto.NewEventDto;

import java.util.List;

public interface EventsService {
    EventDto addEvent(NewEventDto newEvent);

    List<EventDto> getEvents();

    EventDto getEvent(Long eventId);
}
