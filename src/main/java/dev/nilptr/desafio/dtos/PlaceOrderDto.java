package dev.nilptr.desafio.dtos;

import dev.nilptr.desafio.consts.PaymentMethod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@Setter
@ToString
public class PlaceOrderDto {
/*    private String customerEmail;*/
    private String orderId;
    private PaymentMethod paymentMethod;
    private BigInteger total;
/*    private String productID;
    private int amount;*/
}
