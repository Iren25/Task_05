package de.ait.events.services.impl;

import de.ait.events.dto.EventDto;
import de.ait.events.dto.MemberToEventDto;
import de.ait.events.dto.NewEventDto;
import de.ait.events.dto.UserDto;
import de.ait.events.exceptions.RestException;
import de.ait.events.models.Event;
import de.ait.events.models.User;
import de.ait.events.repositories.EventsRepository;
import de.ait.events.repositories.UsersRepository;
import de.ait.events.services.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static de.ait.events.dto.EventDto.from;
import static de.ait.events.dto.UserDto.from;

@RequiredArgsConstructor
@Service
public class EventsServiceImpl implements EventsService {

    private final EventsRepository eventsRepository;

    private final UsersRepository membersRepository;
    @Override
    public EventDto addEvent(NewEventDto newEvent) {

        Event event = Event.builder()
                .name(newEvent.getName())
                .description(newEvent.getDescription())
                .build();

        eventsRepository.save(event);

        return from(event);
    }

    @Override
    public List<EventDto> getEvents() {
        List<Event> events = eventsRepository.findAll();
        return from(events);
    }

    @Override
    public EventDto getEvent(Long eventId) {
        Event event = eventsRepository.findById(eventId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                        "Event with id <" + eventId + "> not found"));
        return from(event);
    }

    @Override
    public List<UserDto> addMemberToEvent(Long eventId, MemberToEventDto memberData) {

        Event event = eventsRepository.findById(eventId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                        "Event with id <" + eventId + "> not found"));

        User member = membersRepository.findById(memberData.getUserId())
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                        "User with id <" + memberData.getUserId() + "> not found"));
        member.getEvents().add(event);
        membersRepository.save(member);
        Set<User> membersOfEvent = membersRepository.findAllByEventsContainsOrderById(event);
        return from(membersOfEvent);
    }

    @Override
    public List<UserDto> getMembersOfEvent(Long eventId) {
        Event event = eventsRepository.findById(eventId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                        "Event with id <" + eventId + "> not found"));
        Set<User> membersOfEvent = membersRepository.findAllByEventsContainsOrderById(event);

        return from(membersOfEvent);
    }
}
