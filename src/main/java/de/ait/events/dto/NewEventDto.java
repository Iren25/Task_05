package de.ait.events.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Schema(name = "NewEvent")
public class NewEventDto {

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

}
