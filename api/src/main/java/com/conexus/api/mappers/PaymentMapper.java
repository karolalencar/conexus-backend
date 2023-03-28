package com.conexus.api.mappers;

import com.conexus.api.domain.Payment;
import com.conexus.api.dto.PaymentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentDto paymetToPaymentDto(Payment payment);

    Payment paymentDtoToPayment(PaymentDto paymentDto);
}
