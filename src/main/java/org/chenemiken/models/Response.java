package org.chenemiken.models;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    protected boolean success;
    protected ResponseStatus status;
    protected String message;
    protected T data;

    public enum ResponseStatus{
        ERROR, SUCCESS
    };
}
