package pe.com.gymconnect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateGymCommand(

        @NotBlank String name,

        @NotBlank String address,

        @NotBlank @Size(max = 10, message = "El número de teléfono no puede tener más de 10 caracteres") String phone

) {
}
