package org.chenemiken.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Response<T> {
    private boolean success;
    private String status;
    private String message;
    private T data;
}
