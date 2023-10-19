package de.ait.events.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.*;

@Data
@Schema(name = "New Area")
public class NewAreaDto {

    @Schema(description = "Name of arena", example = "Hall 1")
    @NotNull
    @NotBlank
    @NotEmpty
    private String name;

}
