package com.max.gerenciadorTcc.domain.repository;

import com.max.gerenciadorTcc.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);

    User findUserByLogin(String login);
}
