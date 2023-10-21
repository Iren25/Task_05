package de.ait.events.services;

import de.ait.events.dto.*;

import java.util.List;

public interface AreasService {
    AreaDto addArea(NewAreaDto newArea);

    List<AreaDto> getAreas();

    EventDto addEventToArea(Long areaId, NewEventDto newEvent);


    AreaDto getArea(Long eventId);

    List<EventDto> getEventsOfArea(Long areaId);

    EventDto deleteEventFromArea(Long areaId, Long eventId);

    EventDto updateEventInArea(Long areaId, Long eventId, UpdateEventDto updateEvent);
}
