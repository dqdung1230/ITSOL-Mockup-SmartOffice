package com.itsol.mockup.web.dto.response.auth;

import com.itsol.mockup.web.dto.response.BaseResponseDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author anhvd_itsol
 */

@Getter
@Setter
public class AuthResponseDTO extends BaseResponseDTO {
    private String username;
    private String password;
    private String token;

    public AuthResponseDTO() {}
}
