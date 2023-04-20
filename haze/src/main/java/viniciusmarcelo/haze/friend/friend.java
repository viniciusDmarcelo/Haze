package viniciusmarcelo.haze.friend;

import jakarta.persistence.*;
import lombok.*;
import viniciusmarcelo.haze.user.User;

import java.util.List;
import java.util.UUID;

@Builder
@Entity(name = "friend ")
@Table(name = "friends")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class friend {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToMany
    private List<User> user;
}
