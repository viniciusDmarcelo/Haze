package viniciusmarcelo.haze.user;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Builder
@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String nickname;

    private String password;

    private boolean active;

    public User(UserSaveDto dto) {
        this.name = dto.name();
        this.nickname = dto.nickname();
        this.password = dto.password();
        this.active = true;
    }
}

