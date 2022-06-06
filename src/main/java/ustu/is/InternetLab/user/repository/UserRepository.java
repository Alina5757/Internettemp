package ustu.is.InternetLab.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ustu.is.InternetLab.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByLoginIgnoreCase(String login);
}