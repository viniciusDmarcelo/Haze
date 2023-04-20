package viniciusmarcelo.haze.review;

import jakarta.persistence.*;
import lombok.*;
import viniciusmarcelo.haze.software.Software;
import viniciusmarcelo.haze.user.User;

import java.util.UUID;

@Builder
@Entity(name = "review")
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Software software;

    private String commentary;

    private Rate rate;

    private boolean active;


}
