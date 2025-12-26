package com.tek.mapper;

import com.tek.entity.Payment;
import com.tek.entity.dto.AddPaymentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment toPayment(AddPaymentDTO paymentDTO);

    AddPaymentDTO toPaymentDTO(Payment payment);
}
