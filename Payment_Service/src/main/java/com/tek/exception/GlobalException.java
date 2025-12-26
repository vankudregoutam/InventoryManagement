package com.tek.exception;

import com.tek.entity.dto.ExceptionResponseDTO;
import com.tek.exception.payment.OrderNotFoundException;
import com.tek.exception.payment.PaymentFailedException;
import com.tek.exception.payment.ServerDownException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> orderNotExistsException(OrderNotFoundException exception, WebRequest request) {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(request.getDescription(false), HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentFailedException.class)
    public ResponseEntity<ExceptionResponseDTO> paymentFailedException(PaymentFailedException exception, WebRequest request) {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServerDownException.class)
    public ResponseEntity<ExceptionResponseDTO> serverDownException(ServerDownException exception, WebRequest request) {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(request.getDescription(false), HttpStatus.SERVICE_UNAVAILABLE, exception.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(responseDTO, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
