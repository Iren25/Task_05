package de.ait.events.controllers;

import de.ait.events.dto.AreaDto;
import de.ait.events.dto.EventDto;
import de.ait.events.dto.NewAreaDto;
import de.ait.events.dto.NewEventDto;
import de.ait.events.services.AreasService;
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
@RequestMapping("/api/areas")
@Tags(value = {
        @Tag(name = "Areas")
})
public class AreasController {

    private final AreasService areasService;

    @Operation(summary = "Creating an area", description = "Available for admin")
    @ApiResponses(@ApiResponse(responseCode = "201",
            description = "Area has been successfully created",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AreaDto.class)))
    )
    @PostMapping
    public ResponseEntity<AreaDto> addArea(@RequestBody NewAreaDto newArea){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(areasService.addArea(newArea));
    }

    @Operation(summary = "Getting a list of areas", description = "Available for all users")
    @GetMapping
    public ResponseEntity<List<AreaDto>> getAreas(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(areasService.getAreas());
    }

    @Operation(summary = "Getting area", description = "Available for all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Request processed successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AreaDto.class))
            ),
            @ApiResponse(responseCode = "404",
                    description = "Area not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AreaDto.class)))
    })
    @GetMapping("/{area-id}")
    public ResponseEntity<AreaDto> getArea(@Parameter(description = "area's id", example = "1")
                                             @PathVariable("area-id") Long areaId){
        return ResponseEntity
                .ok(areasService.getArea(areaId));
    }

    @PostMapping("/{area-id}/events")
    public ResponseEntity<EventDto> addEventToArea(@PathVariable("area-id") Long areaId,
                                                   @RequestBody NewEventDto newEvent) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(areasService.addEventToArea(areaId, newEvent));
    }
}
