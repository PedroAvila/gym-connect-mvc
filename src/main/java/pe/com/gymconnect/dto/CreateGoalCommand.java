package pe.com.gymconnect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateGoalCommand(

                Long gymId,

                @NotBlank @Size(max = 100, message = "El nombre no debe tener más de 100 caractares") String name

) {
}
