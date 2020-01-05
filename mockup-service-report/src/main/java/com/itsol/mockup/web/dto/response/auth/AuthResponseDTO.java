package com.itsol.mockup.web.dto.response.auth;

import com.itsol.mockup.web.dto.response.BaseResponseDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

/**
 * @author anhvd_itsol
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AuthResponseDTO extends BaseResponseDTO {
     String username;
     String password;
     String token;
     Set<String> roles;

}
