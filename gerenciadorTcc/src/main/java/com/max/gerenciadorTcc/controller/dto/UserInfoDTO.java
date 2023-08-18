package com.max.gerenciadorTcc.controller.dto;

import com.max.gerenciadorTcc.domain.enums.UserRole;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDTO {

    private Long id;
    private String login;
    private UserRole role;
}
