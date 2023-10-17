package de.ait.events.dto;

import de.ait.events.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "User", description = "Users data")
public class UserDto {

    @Schema(description = "user's id", example = "1")
    private Long id;

    @Schema(description = "user's name", example = "Oleg")
    private String firstName;

    @Schema(description = "user's lastname", example = "Petrov")
    private String lastName;

    @Schema(description = "user's email", example = "user@mail.com")
    private String email;

    @Schema(description = "user's role", example = "USER")
    private String role;

    public static UserDto from(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();
    }
}
