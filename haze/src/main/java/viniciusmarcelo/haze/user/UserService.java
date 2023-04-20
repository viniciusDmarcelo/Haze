package viniciusmarcelo.haze.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User createUser(UserSaveDto dto) {
        if (dto.name() == null || dto.name().equals("")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (dto.nickname() == null || dto.nickname().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (dto.password() == null || dto.password().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        User user = new User(dto);
        repository.save(user);
        return user;
    }

    public void updateUser(UUID id, UserUpdateDto dto) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        User user = repository.getReferenceById(id);
        if (dto.name() != null) user.setName(dto.name());
        if (dto.nickname() != null) user.setName(dto.nickname());
        repository.save(user);
    }

    public void deleteUser(UUID id) {
        if (repository.existsById(id)) repository.findById(id).get().setActive(false);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void reactivateUser(UUID id) {
        if (repository.existsById(id)) repository.findById(id).get().setActive(true);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Page<UserViewDto> getAll(Pageable pageable) {
        return repository.findAllByActiveTrue(pageable);
    }

    public User findById(UUID id) {
        return repository.findById(id).get();
    }

    public Page<UserViewDto> getByName(String name, Pageable pageable) {
        return repository.findByNameContainingIgnoringCase(name, pageable);
    }

    public Page<UserViewDto> getByNickname(String nickname, Pageable pageable) {
        return repository.findByNicknameContainingIgnoreCase(nickname, pageable);
    }


}
