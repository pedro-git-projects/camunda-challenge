package dev.nilptr.desafio.services;

import dev.nilptr.desafio.consts.PaymentStatus;
import dev.nilptr.desafio.dtos.PlaceOrderDto;
import dev.nilptr.desafio.dtos.ProcessPaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcessPaymentService {
    public Mono<ProcessPaymentDto> processPayment(PlaceOrderDto placeorderDto) {
        return Mono.fromRunnable(() -> {
            var dto = new ProcessPaymentDto();
            log.info("Processing payment with data " + placeorderDto.toString());
            if (placeorderDto.getTotal().mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                dto.setProcessId(placeorderDto.getOrderId());
                dto.setPaymentStatus(PaymentStatus.APPROVED);
                log.info("Output data set to " + dto);
            } else {
                dto.setProcessId(placeorderDto.getOrderId());
                dto.setPaymentStatus(PaymentStatus.REFUSED);
                log.info("Output data set to " + dto);
            }
        });
    }
}
