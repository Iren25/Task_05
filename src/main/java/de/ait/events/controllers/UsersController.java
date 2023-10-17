package de.ait.events.controllers;

import de.ait.events.dto.NewUserDto;
import de.ait.events.dto.StandardResponseDto;
import de.ait.events.dto.UserDto;
import de.ait.events.services.UsersService;
import de.ait.events.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Tags(
        @Tag(name = "Users")
)
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    @Operation(summary = "Users registration", description = "Available for all users")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "User has registered",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class))),
            @ApiResponse(responseCode = "409",
                    description = "User with this email has already exists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class))),
    })

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid NewUserDto newUser){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usersService.register(newUser));
    }
}
