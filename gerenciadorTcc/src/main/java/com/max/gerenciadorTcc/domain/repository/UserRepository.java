package com.max.gerenciadorTcc.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.max.gerenciadorTcc.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long>{

    UserDetails findByLogin(String login);
}
