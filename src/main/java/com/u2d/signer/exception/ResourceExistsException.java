package com.u2d.signer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceExistsException extends RuntimeException{
    public ResourceExistsException(Class classe) {
        super(classe.getSimpleName()+" already exists");
    }
}
