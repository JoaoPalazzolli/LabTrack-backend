package br.com.project.labtrack.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GenerationFailedException extends RuntimeException{
    public GenerationFailedException(String msg){
        super(msg);
    }
}
