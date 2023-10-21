package de.ait.events.dto;

import de.ait.events.models.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
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
    @Schema(description = "start time", example = "9:00")
    private String startTime;
    @Schema(description = "finish time", example = "13:00")
    private String finishTime;
    @Schema(description = "day of week", example = "MONDAY")
    private String dayOfWeek;

    private Long areaId;

    public static EventDto from(Event event){
        EventDto result = EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .startTime(event.getStartTime().toString())
                .finishTime(event.getFinishTime().toString())
                .dayOfWeek(event.getDayOfWeek().toString())
                .build();

        if(event.getArea() != null){
            result.setAreaId(event.getArea().getId());
        }
        return result;
    }

    public static List<EventDto> from(List<Event> events){
        return events
                .stream()
                .map(EventDto::from)
                .collect(Collectors.toList());
    }

   public static List<EventDto> from(Set<Event> events) {
        return events
                .stream()
                .map(EventDto::from)
                .collect(Collectors.toList());
    }
}
