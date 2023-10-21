package de.ait.events.services.impl;

import de.ait.events.dto.*;
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
import java.util.Set;

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
        Area area = getAreaOrElseThrow(areaId);
        return AreaDto.from(area);
    }

    @Override
    public List<EventDto> getEventsOfArea(Long areaId) {
        Area area = getAreaOrElseThrow(areaId);
        Set<Event> events = eventsRepository.findAllByAreaOrderById(area);
        //Set<Event> events = area.getEvents();

        return from(events);
    }

    @Override
    public EventDto deleteEventFromArea(Long areaId, Long eventId) {
        Area area = getAreaOrElseThrow(areaId);

        Event event = eventsRepository.findByAreaAndId(area, eventId).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                "Event with id <" + eventId + "> not found in area with id <" + areaId + ">"));

        event.setArea(null);
        eventsRepository.save(event);
        return from(event);

        /*
         Set<Event> eventsOfArea = area.getEvents();
        for(Event event : eventsOfArea) {
            if (event.getId().equals(eventId)){
                event.setArea(null);
                eventsRepository.save(event);
                return from(event);
            }
        }
        throw new RestException(HttpStatus.NOT_FOUND, "Event with id <" + eventId + "> not found in area with id <" + areaId + ">");
         */
    }

    @Override
    public EventDto updateEventInArea(Long areaId, Long eventId, UpdateEventDto updateEvent) {
        Area area = getAreaOrElseThrow(areaId);

        Event event = eventsRepository.findByAreaAndId(area, eventId).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                "Event with id <" + eventId + "> not found in area with id <" + areaId + ">"));

        event.setName(updateEvent.getName());
        event.setDescription(updateEvent.getDescription());
        event.setStartTime(LocalTime.parse(updateEvent.getStartTime()));
        event.setFinishTime(LocalTime.parse(updateEvent.getFinishTime()));
        event.setDayOfWeek(DayOfWeek.valueOf(updateEvent.getDayOfWeek()));

        eventsRepository.save(event);

        return from(event);


        /*
        Set<Event> eventsOfArea = area.getEvents();
        for (Event event : eventsOfArea) {
            if (event.getId().equals(eventId)){
                event.setName(updateEvent.getName());
                event.setDescription(updateEvent.getDescription());
                event.setStartTime(LocalTime.parse(updateEvent.getStartTime()));
                event.setFinishTime(LocalTime.parse(updateEvent.getFinishTime()));
                event.setDayOfWeek(DayOfWeek.valueOf(updateEvent.getDayOfWeek()));

                eventsRepository.save(event);

                return from(event);
            }
        }
        throw new RestException(HttpStatus.NOT_FOUND, "Event with id <" + eventId + "> not found in area with id <" + areaId + ">");

         */
    }


    private Area getAreaOrElseThrow(Long areaId) {
        return areasRepository.findById(areaId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Area with id <" + areaId + "> not found"));
    }

    @Override
    public EventDto addEventToArea(Long areaId, NewEventDto newEvent) {
        Area area = getAreaOrElseThrow(areaId);

        Event event;
        if(newEvent.getExistsEventId() == null) {
            event = Event.builder()
                    .name(newEvent.getName())
                    .description(newEvent.getDescription())
                    .dayOfWeek(DayOfWeek.valueOf(newEvent.getDayOfWeek()))
                    .startTime(LocalTime.parse(newEvent.getStartTime()))
                    .finishTime(LocalTime.parse(newEvent.getFinishTime()))
                    .area(area)
                    .build();
        } else {
            event = eventsRepository.findById(newEvent.getExistsEventId())
                    .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                            "Event with id <" + newEvent.getExistsEventId() + "> not found"));
            event.setArea(area);
        }

        eventsRepository.save(event);

        return from(event);
    }


}
