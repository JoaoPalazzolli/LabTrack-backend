package br.com.project.labtrack.infra.exceptions.handler;

import br.com.project.labtrack.infra.exceptions.GenerationFailedException;
import br.com.project.labtrack.infra.exceptions.InvalidJwtAuthenticationException;
import br.com.project.labtrack.infra.exceptions.ObjectNotFound;
import br.com.project.labtrack.infra.exceptions.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@RestControllerAdvice
public class CustomizeHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseException> allExceptionHandler(WebRequest request, Exception ex){

        var responseException = ResponseException.builder()
                .mensagem(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .detalhes(request.getDescription(false))
                .build();

        return new ResponseEntity<>(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ObjectNotFound.class)
    public ResponseEntity<ResponseException> objectNotFoundExceptionHandler(WebRequest request, Exception ex){

        var responseException = ResponseException.builder()
                .mensagem(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .detalhes(request.getDescription(false))
                .build();

        return new ResponseEntity<>(responseException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public ResponseEntity<ResponseException> invalidJwtAuthenticationExceptionHandler(WebRequest request, Exception ex){

        var responseException = ResponseException.builder()
                .mensagem(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .detalhes(request.getDescription(false))
                .build();

        return new ResponseEntity<>(responseException, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(GenerationFailedException.class)
    public ResponseEntity<ResponseException> generationFailedExceptionHandler(WebRequest request, Exception ex){

        var responseException = ResponseException.builder()
                .mensagem(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .detalhes(request.getDescription(false))
                .build();

        return new ResponseEntity<>(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
