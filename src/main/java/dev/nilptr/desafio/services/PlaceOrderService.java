package dev.nilptr.desafio.services;

import dev.nilptr.desafio.consts.PaymentMethod;
import dev.nilptr.desafio.dtos.PlaceOrderDto;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.PublishMessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceOrderService {
    private final ZeebeClient zeebeClient;

    public Mono<PublishMessageResponse> placeOrder(PlaceOrderDto placeOrderDto) {
        log.info("Called " + PlaceOrderService.class);

        String orderId = placeOrderDto.getOrderId();
        String paymentMethod = placeOrderDto.getPaymentMethod().name();
        String total = placeOrderDto.getTotal().toString();

        var variables = Map.of("orderId", orderId, "paymentMethod", paymentMethod, "total", total);

        ZeebeFuture<PublishMessageResponse> future = zeebeClient.newPublishMessageCommand().messageName("orderPlaced").correlationKey(orderId).variables(variables).send();
        return Mono.create(sink -> future.whenComplete((publishMessageResponse, throwable) -> {
            if (throwable != null) {
                log.error("Failed with " + throwable);
                sink.error(throwable);
            } else {
                log.info("Successfully published message!");
                sink.success(publishMessageResponse);
            }
        }));
    }
}
