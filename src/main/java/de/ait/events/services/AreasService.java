package de.ait.events.services;

import de.ait.events.dto.AreaDto;

import de.ait.events.dto.EventDto;
import de.ait.events.dto.NewAreaDto;
import de.ait.events.dto.NewEventDto;

import java.util.List;

public interface AreasService {
    AreaDto addArea(NewAreaDto newArea);

    List<AreaDto> getAreas();

    EventDto addEventToArea(Long areaId, NewEventDto newEvent);


    AreaDto getArea(Long eventId);
}
