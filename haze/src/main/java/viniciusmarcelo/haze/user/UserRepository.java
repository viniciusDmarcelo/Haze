package viniciusmarcelo.haze.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Page<UserViewDto> findAllByActiveTrue(Pageable pageable);

    Page<UserViewDto> findByNameContainingIgnoringCase(String name, Pageable pageable);

    Page<UserViewDto> findByNicknameContainingIgnoreCase(String nickname, Pageable pageable);


}
