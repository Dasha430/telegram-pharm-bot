package ua.com.alevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.alevel.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByChatId(Long id);
}
