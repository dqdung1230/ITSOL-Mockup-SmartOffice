package com.itsol.mockup.web.dto.request.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * @author anhvd_itsol
 */

@Getter
@Setter
public class AuthRequestDTO {
    private String username;
    private String password;

    public AuthRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
