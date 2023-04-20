package viniciusmarcelo.haze.software;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Service
public class SoftwareService {

    @Autowired
    private SoftwareRepository repository;

    public Software createSoftware(SoftwareSaveDto dto) {
        if (dto.title() == null || dto.title().equals("")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (dto.description() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (dto.baseValue() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Software software = new Software(dto);
        repository.save(software);
        return software;
    }

    public void updateSoftware(UUID id, SoftwareUpdateDto dto) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (repository.findById((id)).isPresent()) {
            Software software = repository.findById(id).get();
            if (dto.title() != null) software.setTitle(dto.title());
            if (dto.description() != null) software.setDescription(dto.description());
            if (dto.baseValue() != null) software.setBaseValue(BigDecimal.valueOf(dto.baseValue()));
            if (dto.sale() != null) software.setSale(dto.sale());
            if (dto.sale() != null && dto.sale() > 0) {
                BigDecimal saleValue = BigDecimal.valueOf(dto.sale()).divide(BigDecimal.valueOf(100), RoundingMode.DOWN).multiply(software.getBaseValue());
                software.setActualValue(software.getBaseValue().subtract(saleValue));
                repository.save(software);
            }
            repository.saveAndFlush(software);
        }
    }

    public void deleteSoftware(UUID id) {
        if (repository.existsById(id)) repository.findById(id).get().setActive(false);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void reactivateSoftware(UUID id) {
        if (repository.existsById(id)) repository.findById(id).get().setActive(true);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Page<SoftwareViewDto> getAll(Pageable pageable) {
        return repository.findAllByActiveTrue(pageable);
    }

    public Software findById(UUID id) {
        return repository.findById(id).get();
    }

    public Page<SoftwareViewDto> getByTitle(String title, Pageable pageable) {
        return repository.findByTitleContainingIgnoringCase(title, pageable);
    }

    public Page<SoftwareViewDto> getByRating(Rating rating, Pageable pageable) {
        return repository.findByRating(rating, pageable);
    }

    public Page<SoftwareViewDto> getGreaterThan(BigDecimal value, Pageable pageable) {
        return repository.findGreaterThanValue(value, pageable);
    }

    public Page<SoftwareViewDto> getMinorThanValue(BigDecimal value, Pageable pageable) {
        return repository.findMinorThanValue(value, pageable);
    }

    public Page<SoftwareViewDto> getBetweenValues(BigDecimal value1, BigDecimal value2, Pageable pageable) {
        return repository.findBetweenValues(value1, value2, pageable);
    }

}
