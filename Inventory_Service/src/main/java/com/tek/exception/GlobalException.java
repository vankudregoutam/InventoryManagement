package com.tek.exception;

import com.tek.entity.dto.ExceptionResponseDTO;
import com.tek.exception.inventory.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> productNotFoundException(ProductNotFoundException exception, WebRequest request) {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(request.getDescription(false), HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }
}
