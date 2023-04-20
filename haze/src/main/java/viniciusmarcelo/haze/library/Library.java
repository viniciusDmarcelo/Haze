package viniciusmarcelo.haze.library;

import jakarta.persistence.*;
import lombok.*;
import viniciusmarcelo.haze.software.Software;
import viniciusmarcelo.haze.user.User;

import java.util.List;
import java.util.UUID;

@Builder
@Entity(name = "library ")
@Table(name = "libraries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Software> software;

}
