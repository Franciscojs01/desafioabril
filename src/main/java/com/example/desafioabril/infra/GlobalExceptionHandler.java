package com.example.desafioabril.infra;

import com.example.desafioabril.exceptions.ItemDuplicadoException;
import com.example.desafioabril.exceptions.ItemNotFoundException;
import com.example.desafioabril.exceptions.UsuarioDuplicadoException;
import com.example.desafioabril.exceptions.UsuarioNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleUsuarioNotFoundException(UsuarioNotFoundException ex, WebRequest request) {
        RestErrorMessage errorResponse = new RestErrorMessage(
                HttpStatus.NOT_FOUND.value(),"Usuário não encontrado", ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(UsuarioDuplicadoException.class)
    public ResponseEntity<RestErrorMessage> handleUsuarioDuplicadoException(UsuarioDuplicadoException ex, WebRequest request) {
        RestErrorMessage errorResponse = new RestErrorMessage(
                HttpStatus.CONFLICT.value(), "Usuário já cadastrado", ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleItemNotFoundException(ItemNotFoundException ex, WebRequest request) {
        RestErrorMessage errorResponse = new RestErrorMessage(
            HttpStatus.NOT_FOUND.value(), "Item não encontrado", ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ItemDuplicadoException.class)
    public ResponseEntity<RestErrorMessage> handleItemDuplicadoException(ItemDuplicadoException ex, WebRequest request) {
        RestErrorMessage errorResponse = new RestErrorMessage(
                HttpStatus.CONFLICT.value(), "Item já cadastrado", ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }





}
