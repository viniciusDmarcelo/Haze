package viniciusmarcelo.haze.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid UserSaveDto dto, UriComponentsBuilder uriComponentsBuilder) {
        User user = service.createUser(dto);
        URI uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body("User created!\n" + "Name: " + user.getName() + "\n" + "Nickname: " + user.getNickname());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateUser(@PathVariable String id, @Valid @RequestBody UserUpdateDto dto) {
        service.updateUser(UUID.fromString(id), dto);
        return ResponseEntity.ok("User updated!");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable String id) {
        service.deleteUser(UUID.fromString(id));
        return ResponseEntity.ok("User deleted");
    }

    @PostMapping("/reactivateUser/{id}")
    @Transactional
    public ResponseEntity reactivateUser(@PathVariable String id) {
        service.reactivateUser(UUID.fromString(id));
        return ResponseEntity.ok("User Reactivated");
    }

    @GetMapping
    public Page<UserViewDto> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable String id, Pageable pageable) {
        return service.findById(UUID.fromString(id));
    }

    @GetMapping("/getByName/{name}")
    public Page<UserViewDto> getByName(@PathVariable String name, Pageable pageable) {
        return service.getByName(name, pageable);
    }

    @GetMapping("/getByNickname/{nickname}")
    public Page<UserViewDto> getByNickname(@PathVariable String nickname, Pageable pageable) {
        return service.getByNickname(nickname, pageable);
    }

}
