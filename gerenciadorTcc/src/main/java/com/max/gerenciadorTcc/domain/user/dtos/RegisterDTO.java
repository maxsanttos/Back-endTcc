package com.max.gerenciadorTcc.domain.user.dtos;

import com.max.gerenciadorTcc.domain.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {

}
