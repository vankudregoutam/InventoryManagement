package com.tek.repository;

import com.tek.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
    boolean existsByOrderId(Long orderId);

    Optional<Payment> findByOrderId(Long orderId);
}
