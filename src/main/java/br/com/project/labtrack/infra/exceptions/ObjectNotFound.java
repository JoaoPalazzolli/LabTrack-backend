package br.com.project.labtrack.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFound extends RuntimeException {

    public ObjectNotFound(String msg){
        super(msg);
    }
}
