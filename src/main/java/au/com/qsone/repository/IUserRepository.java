package au.com.qsone.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import au.com.qsone.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}