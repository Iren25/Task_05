package de.ait.events.controllers;

import de.ait.events.EventsApplication;
import de.ait.events.dto.EventDto;
import de.ait.events.dto.MemberToEventDto;
import de.ait.events.dto.NewEventDto;
import de.ait.events.dto.UserDto;
import de.ait.events.services.AreasService;
import de.ait.events.services.EventsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
@Tags(value = {
        @Tag(name = "Events")
})
public class EventsController {

    private final EventsService eventsService;
    private final AreasService areasService;

    @Operation(summary = "Creating an event", description = "Available for admin")
    @ApiResponses(@ApiResponse(responseCode = "201",
            description = "Event has been successfully created",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = EventDto.class)))
    )
    @PostMapping
    public ResponseEntity<EventDto> addEvent(@RequestBody NewEventDto newEvent){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventsService.addEvent(newEvent));
    }

    @Operation(summary = "Getting a list of events", description = "Available for all users")
    @GetMapping
    public ResponseEntity<List<EventDto>> getEvents(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(eventsService.getEvents());
    }

    @Operation(summary = "Getting event", description = "Available for all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Request processed successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class))
            ),
            @ApiResponse(responseCode = "404",
                    description = "Event not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class)))
    })
    @GetMapping("/{event-id}")
    public ResponseEntity<EventDto> getEvent(@Parameter(description = "event's id", example = "1")
                                                 @PathVariable("event-id") Long eventId){
        return ResponseEntity
                .ok(eventsService.getEvent(eventId));
    }

    @PostMapping("/{event-id}/members")
    public ResponseEntity<List<UserDto>> addMemberToEvent(@PathVariable("event-id") Long eventId,
                                                          @RequestBody MemberToEventDto memberData) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventsService.addMemberToEvent(eventId, memberData));
    }

    @GetMapping("/{event-id}/members")
    public ResponseEntity<List<UserDto>> getMembersOfEvent(@PathVariable("event-id") Long eventId){
        return ResponseEntity.ok(eventsService.getMembersOfEvent(eventId));
    }

}
