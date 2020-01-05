package com.itsol.mockup.web.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author anhvd_itsol
 */

@Getter
@Setter
public class GetSingleDataResponseDTO<T> extends BaseResponseDTO {
    private T data;

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

    @Override
    public String toString() {
        return "GetSingleResponse {" +
                "data = " + data +
                ", message = " + message + '\'' +
                ", code = " + code +
                "}";
    }
}
