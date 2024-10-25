package pe.com.gymconnect.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateGoalCommand(
        Long id,

        Long gymId,

        int code,

        @NotBlank String name) {

}
