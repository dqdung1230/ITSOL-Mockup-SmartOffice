package com.itsol.mockup.web.dto.response;

import com.itsol.mockup.utils.Constants;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author anhvd_itsol
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseResponseDTO {
     String message;
     String code;

    public void setSuccess() {
        this.code = Constants.ApiErrorCode.SUCCESS;
        this.message = Constants.ApiErrorDesc.SUCCESS;
    }

    public void setSuccess(String msg) {
        this.code = Constants.ApiErrorCode.SUCCESS;
        this.message = msg;
    }

    public void setFailed() {
        this.code = Constants.ApiErrorCode.ERROR;
        this.message = Constants.ApiErrorDesc.ERROR;
    }

    public void setFailed(String msg) {
        this.code = Constants.ApiErrorCode.ERROR;
        this.message = msg;
    }
}
