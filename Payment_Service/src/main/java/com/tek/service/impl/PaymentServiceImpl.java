package com.tek.service.impl;

import com.tek.client.InventoryClient;
import com.tek.client.OrderClient;
import com.tek.entity.Payment;
import com.tek.entity.Status;
import com.tek.entity.dto.ResponseDTO;
import com.tek.exception.payment.OrderNotFoundException;
import com.tek.exception.payment.PaymentFailedException;
import com.tek.exception.payment.ServerDownException;
import com.tek.repository.PaymentRepo;
import com.tek.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepo repo;

    private final InventoryClient inventoryClient;

    private final OrderClient orderClient;

    @Override
    public void processPayment(Long orderId, Double amount) {
        if (repo.findByOrderId(orderId).isPresent()) {
            return;
        }

        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(amount);
        payment.setCreatedDate(LocalDate.now());
        payment.setStatus(Status.PENDING);

        repo.save(payment);

        boolean isApproved = true;

        if (isApproved) {
            payment.setStatus(Status.SUCCESS);
            repo.save(payment);

            try {
                orderClient.sendCallback(orderId, "PAID");
            } catch (Exception e) {
                System.err.println("Failed to send Success callback: " + e.getMessage());
            }
        } else {
            payment.setStatus(Status.FAILED);
            repo.save(payment);

            try {
                orderClient.sendCallback(orderId, "REJECTED");
            } catch (Exception e) {
                System.err.println("Failed to send Failure callback: " + e.getMessage());
            }
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> doPayment(Long orderId) {
        Payment payment = repo.findByOrderId(orderId)
                .orElseThrow(() -> new OrderNotFoundException("No payment record found for Order ID: " + orderId));

            if (payment.getStatus() == Status.SUCCESS) {
            return ResponseEntity.ok(new ResponseDTO("PAY_200", "Payment already successful"));
        }

        payment.setStatus(Status.SUCCESS);
        repo.save(payment);

        try {
            orderClient.sendCallback(orderId, "PAID");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseDTO("PAY_500", "Payment successful but Callback failed: " + e.getMessage()));
        }

        return ResponseEntity.ok(new ResponseDTO("PAY_200", "Payment Retry Successful"));
    }

    @Override
    public void addPayment(Long orderId, Double amount) {
        try {
            processPayment(orderId, amount);
        } catch (Exception e) {
            throw new ServerDownException("Payment service unavailable");
        }
    }

}
