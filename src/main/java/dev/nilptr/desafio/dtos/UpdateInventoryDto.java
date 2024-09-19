package dev.nilptr.desafio.dtos;

import dev.nilptr.desafio.consts.OrderStatus;
import dev.nilptr.desafio.consts.PaymentStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class UpdateInventoryDto {
    private String productId;
    private int amount;
    private String customerEmail;
    private PaymentStatus paymentStatus;
    private String orderId;
    private OrderStatus orderStatus;

    public UpdateInventoryDto(ProcessPaymentDto dto) {
        this.productId = dto.getProductId();
        this.amount = dto.getAmount();
        this.customerEmail = dto.getCustomerEmail();
        this.paymentStatus = dto.getPaymentStatus();
        this.orderId = dto.getOrderId();
    }

}
