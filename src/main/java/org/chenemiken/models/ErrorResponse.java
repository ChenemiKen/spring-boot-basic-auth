package org.chenemiken.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse<T> extends Response<T>{
    private LocalDateTime timestamp;

    @Builder(builderMethodName = "DefaultErrorResponseBuilder")
    public ErrorResponse(String message, T data){
        super(false, ResponseStatus.ERROR, message, data);
        this.timestamp = LocalDateTime.now();
    }

    @Builder(builderMethodName = "ErrorResponseBuilder")
    public ErrorResponse(boolean success, ResponseStatus status, String message,
                         T data, LocalDateTime timestamp){
        super(success, status, message, data);
        this.timestamp = timestamp;
    }


}
