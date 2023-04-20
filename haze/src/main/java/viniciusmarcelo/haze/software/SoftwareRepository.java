package viniciusmarcelo.haze.software;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface SoftwareRepository extends JpaRepository<Software, UUID> {

    Page<SoftwareViewDto> findAllByActiveTrue(Pageable pageable);
    Page<SoftwareViewDto> findByTitleContainingIgnoringCase(String title, Pageable pageable);
    Page<SoftwareViewDto> findByRating(Rating rating, Pageable pageable);
    @Query(value = "SELECT s.* FROM software s where s.value >= :value --#pageable\n", nativeQuery = true)
    Page<SoftwareViewDto> findGreaterThanValue(BigDecimal value, Pageable paginacao);
    @Query(value = "SELECT s.* FROM software s where s.value <= :value --#pageable\n", nativeQuery = true)
    Page<SoftwareViewDto> findMinorThanValue(BigDecimal value, Pageable paginacao);
    @Query(value = "SELECT s.* FROM software s where s.value >= :value1 and s.value <= :value2 --#pageable\n", nativeQuery = true)
    Page<SoftwareViewDto> findBetweenValues(BigDecimal value1, BigDecimal value2, Pageable paginacao);

}
