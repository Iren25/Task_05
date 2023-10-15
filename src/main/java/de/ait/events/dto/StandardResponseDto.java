package de.ait.events.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Message", description = "Message from server")
public class StandardResponseDto {
    @Schema(description = "Message about error, changed statement, etc.", example = "Event not found")
    private String message;
}

