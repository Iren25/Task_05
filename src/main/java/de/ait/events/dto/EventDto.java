package de.ait.events.dto;

import de.ait.events.models.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Event", description = "Description of course")
public class EventDto {

    @Schema(description = "event's id", example = "1")
    private Long id;
    @Schema(description = "Name of event", example = "Concert")
    private String name;
    @Schema(description = "description of event", example = "Bi-2 band")
    private String description;

    public static EventDto from(Event event){
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .build();
    }

    public static List<EventDto> from(List<Event> events){
        return events
                .stream()
                .map(EventDto::from)
                .collect(Collectors.toList());
    }
}
