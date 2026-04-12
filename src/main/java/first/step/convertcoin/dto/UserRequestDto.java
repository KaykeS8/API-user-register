package first.step.convertcoin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(@NotBlank String name, @Email @NotBlank String email) {
}