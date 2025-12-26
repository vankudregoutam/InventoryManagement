package com.tek.exception;

import com.tek.entity.dto.ExceptionResponseDTO;
import com.tek.exception.product.ProductAlreadyExistsException;
import com.tek.exception.product.ProductNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDTO> productExists(ProductAlreadyExistsException exception, WebRequest request) {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(request.getDescription(false), HttpStatus.ALREADY_REPORTED, exception.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(responseDTO, HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(ProductNotExistsException.class)
    public ResponseEntity<ExceptionResponseDTO> productNotExists(ProductNotExistsException exception, WebRequest request) {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(request.getDescription(false), HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }
}
