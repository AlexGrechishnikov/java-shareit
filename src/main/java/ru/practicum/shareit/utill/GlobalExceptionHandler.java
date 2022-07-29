package ru.practicum.shareit.utill;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.practicum.shareit.utill.exceptions.AccessException;
import ru.practicum.shareit.utill.exceptions.AlreadyExistsException;
import ru.practicum.shareit.utill.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> badRequest(final NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Object> badRequest(final AlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Object> notOwnerItemEdit(final AccessException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Object> badValidation(final MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> list = new ArrayList<>();
        for (FieldError error : fieldErrors) {
            StringBuilder sb = new StringBuilder("[");
            sb.append(error.getObjectName()).append("] ")
                    .append(error.getField()).append(" = ")
                    .append(error.getRejectedValue()).append(" (")
                    .append(error.getDefaultMessage()).append(")");
            list.add(sb.toString());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(list);
    }
}