package viniciusmarcelo.haze.software;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SoftwareSaveDto(

        @NotBlank String title,
        @NotBlank String description,
        @NotNull Double baseValue) {

}

