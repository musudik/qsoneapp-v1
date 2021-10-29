package au.com.qsone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.qsone.entity.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}