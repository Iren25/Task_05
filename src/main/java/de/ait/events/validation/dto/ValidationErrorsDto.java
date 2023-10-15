package de.ait.events.validation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "ValidationErrors", description = "Information of validation errors")
public class ValidationErrorsDto {
    @Schema(description = "Validation errors list")
    private List<ValidationErrorDto> errors;
}
