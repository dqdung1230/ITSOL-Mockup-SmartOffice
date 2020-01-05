package com.itsol.mockup.web.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author anhvd_itsol
 */

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetSingleDataResponseDTO<T> extends BaseResponseDTO {
     T data;

    private void setSuccess(T data) {
        super.setSuccess();
        this.data = data;
    }

    public void setResult(T data) {
        if (data == null)
            super.setFailed();
        else
            this.setSuccess(data);
    }


}
