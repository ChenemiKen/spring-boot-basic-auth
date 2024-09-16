package org.chenemiken.exceptions;

public class ModelAlreadyExistException extends RuntimeException{
    public ModelAlreadyExistException(String message){
        super(message);
    }
}
