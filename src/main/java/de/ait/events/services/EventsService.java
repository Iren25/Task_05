package de.ait.events.services;

import de.ait.events.dto.EventDto;
import de.ait.events.dto.MemberToEventDto;
import de.ait.events.dto.NewEventDto;
import de.ait.events.dto.UserDto;

import java.util.List;

public interface EventsService {
    EventDto addEvent(NewEventDto newEvent);

    List<EventDto> getEvents();

    EventDto getEvent(Long eventId);

    List<UserDto> addMemberToEvent(Long eventId, MemberToEventDto memberData);

    List<UserDto> getMembersOfEvent(Long eventId);
}
