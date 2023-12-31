package de.ait.events.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;


@Data
@Schema(name = "New Event")
public class NewEventDto {

    @Schema(description = "Идентификатор существующего события, если задано - остальные поля уже существуют")
    private Long existsEventId;

    @Schema(description = "Name of event", example = "Concert")
    @NotNull
    @NotBlank
    @NotEmpty
    private String name;

    @Schema(description = "description of event", example = "Bi-2 band")
    @Size(min = 5, max = 500)
    @NotNull
    @NotBlank
    private String description;

    @Schema(description = "start time", example = "09:00")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$")
    private String startTime;

    @Schema(description = "finish time", example = "12:00")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$")
    private String finishTime;

    @Schema(description = "day of week", example = "MONDAY")
    //@Pattern(regexp = "^(MONDAY|TUESDAY|WEDNESDAY|THURSDAY|FRIDAY|SATURDAY|SUNDAY)$")
    private String dayOfWeek;

}
