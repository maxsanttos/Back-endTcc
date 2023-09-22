package com.max.gerenciadorTcc.domain.users.dto;


import com.max.gerenciadorTcc.domain.users.UserRole;
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
