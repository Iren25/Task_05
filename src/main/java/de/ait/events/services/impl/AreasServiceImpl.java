package de.ait.events.services.impl;

import de.ait.events.dto.AreaDto;
import de.ait.events.dto.EventDto;
import de.ait.events.dto.NewAreaDto;
import de.ait.events.dto.NewEventDto;
import de.ait.events.exceptions.RestException;
import de.ait.events.models.Area;
import de.ait.events.models.Event;
import de.ait.events.repositories.AreasRepository;
import de.ait.events.repositories.EventsRepository;
import de.ait.events.services.AreasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import static de.ait.events.dto.EventDto.from;

@RequiredArgsConstructor
@Service
public class AreasServiceImpl implements AreasService {

    private final AreasRepository areasRepository;
    private final EventsRepository eventsRepository;


    @Override
    public AreaDto addArea(NewAreaDto newArea) {
        Area area = Area.builder()
                .name(newArea.getName())
                .build();
        areasRepository.save(area);

        return AreaDto.from(area);
    }

    @Override
    public List<AreaDto> getAreas() {
        List<Area> areas = areasRepository.findAll();
        return AreaDto.from(areas);
    }

    @Override
    public AreaDto getArea(Long areaId) {
        Area area = areasRepository.findById(areaId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Area with id <" + areaId + "> not found"));
        return AreaDto.from(area);
    }

    @Override
    public EventDto addEventToArea(Long areaId, NewEventDto newEvent) {
        Area area = areasRepository.findById(areaId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Area with id <" + areaId + "> not found"));

        Event event = Event.builder()
                .name(newEvent.getName())
                .description(newEvent.getDescription())
                .dayOfWeek(DayOfWeek.valueOf(newEvent.getDayOfWeek()))
                .startTime(LocalTime.parse(newEvent.getStartTime()))
                .finishTime(LocalTime.parse(newEvent.getFinishTime()))
                .area(area)
                .build();
        eventsRepository.save(event);

        return from(event);
    }


}
