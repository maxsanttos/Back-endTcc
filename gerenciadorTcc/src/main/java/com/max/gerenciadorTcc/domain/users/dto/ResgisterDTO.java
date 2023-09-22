package com.max.gerenciadorTcc.domain.users.dto;

import com.max.gerenciadorTcc.domain.users.UserRole;

public record ResgisterDTO(String login, String password, UserRole role) {
}
