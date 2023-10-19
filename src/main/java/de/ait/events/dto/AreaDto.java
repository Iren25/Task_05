package de.ait.events.dto;

import de.ait.events.models.Area;
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
@Schema(name = "Area")
public class AreaDto {

    @Schema(description = "Area's id", example = "1")
    private Long id;
    @Schema(description = "Area's name", example = "Hall 1")
    private String name;

    public static AreaDto from(Area area){
        return AreaDto.builder()
                .id(area.getId())
                .name(area.getName())
                .build();
    }

    public static List<AreaDto> from(List<Area> areas){
        return areas
                .stream()
                .map(AreaDto::from)
                .collect(Collectors.toList());
    }
}
