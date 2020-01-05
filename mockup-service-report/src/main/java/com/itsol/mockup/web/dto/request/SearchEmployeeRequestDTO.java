package com.itsol.mockup.web.dto.request;

import com.itsol.mockup.web.dto.BaseDTO;
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
public class SearchEmployeeRequestDTO extends BaseDTO {
     String userName;
     String fullName;
}
