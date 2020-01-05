package com.itsol.mockup.web.dto.request.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author anhvd_itsol
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthRequestDTO {
     String username;
     String password;

}
