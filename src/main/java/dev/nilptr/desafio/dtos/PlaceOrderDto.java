package dev.nilptr.desafio.dtos;

import dev.nilptr.desafio.consts.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class PlaceOrderDto {
    private String orderId;
    private PaymentMethod paymentMethod;
    private BigInteger total;
}
