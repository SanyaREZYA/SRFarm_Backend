package com.srsoft.srfarm.api.login.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class LoginRequestDTO {
    private String username;
    private String password;
}
