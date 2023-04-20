package viniciusmarcelo.haze.software;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Entity(name = "software")
@Table(name = "softwares")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Software {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    @Column(columnDefinition="TEXT")
    private String description;
    private BigDecimal baseValue;
    private BigDecimal actualValue;
    private Integer sale;
    @Enumerated(EnumType.STRING)
    private Rating rating;
    private boolean active;

    public Software(SoftwareSaveDto dto) {
        this.title = dto.title();
        this.description = dto.description();
        this.baseValue = BigDecimal.valueOf(dto.baseValue());
        this.actualValue = baseValue;
        this.sale = 0;
        this.rating = null;
        this.active = true;
    }
}
