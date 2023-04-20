package viniciusmarcelo.haze.software;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

@Controller
@RequestMapping("/software")
public class SoftwareController {

    @Autowired
    private SoftwareService service;

    @PostMapping
    @Transactional
    public ResponseEntity createSoftware(@RequestBody @Valid SoftwareSaveDto dto, UriComponentsBuilder uriComponentsBuilder) {
        Software software = service.createSoftware(dto);
        URI uri = uriComponentsBuilder.path("/software/{id}").buildAndExpand(software.getId()).toUri();
        return ResponseEntity.created(uri)
                .body("Software created!\n" +
                        "Title: " + software.getTitle() + "\n" +
                        "Description: " + software.getDescription() + "\n" +
                        "Value: " + software.getBaseValue());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateSoftware(@PathVariable String id, @Valid @RequestBody SoftwareUpdateDto dto) {
        service.updateSoftware(UUID.fromString(id), dto);
        return ResponseEntity.ok("Software updated!");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteSoftware(@PathVariable String id) {
        service.deleteSoftware(UUID.fromString(id));
        return ResponseEntity.ok("Software deleted");
    }

    @PostMapping("/reactivateUser/{id}")
    @Transactional
    public ResponseEntity reactivateSoftware(@PathVariable String id) {
        service.reactivateSoftware(UUID.fromString(id));
        return ResponseEntity.ok("Software Reactivated");
    }

    @GetMapping
    public Page<SoftwareViewDto> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/findById/{id}")
    public Software findById(@PathVariable String id, Pageable pageable) {
        return service.findById(UUID.fromString(id));
    }

    @GetMapping("/getByTitle/{title}")
    public Page<SoftwareViewDto> getByTitle(@PathVariable String title, Pageable pageable) {
        return service.getByTitle(title, pageable);
    }

    @GetMapping("/getByRating/{rating}")
    public Page<SoftwareViewDto> getByRating(@PathVariable Rating rating, Pageable pageable) {
        return service.getByRating(rating, pageable);
    }

    @GetMapping("/getByValuesBetween")
    public Page<SoftwareViewDto> getByValuesBetween(@RequestBody Double value1, Double value2, Pageable pageable) {
        return service.getBetweenValues(BigDecimal.valueOf(value1), BigDecimal.valueOf(value2), pageable);
    }

    @GetMapping("/getByGreaterThanValue")
    public Page<SoftwareViewDto> getByGreaterThanValue(@PathVariable Double value, Pageable pageable) {
        return service.getGreaterThan(BigDecimal.valueOf(value), pageable);
    }

    @GetMapping("/getByMinorThanValue")
    public Page<SoftwareViewDto> getByMinorThanValue(@PathVariable Double value, Pageable pageable) {
        return service.getMinorThanValue(BigDecimal.valueOf(value), pageable);
    }

}
