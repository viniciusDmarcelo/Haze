package viniciusmarcelo.haze.user;

import jakarta.validation.constraints.NotBlank;

public record UserSaveDto(

        @NotBlank String name,
        @NotBlank String nickname,
        @NotBlank String password){

}

