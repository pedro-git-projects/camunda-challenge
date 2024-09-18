package dev.nilptr.desafio.workers;

import dev.nilptr.desafio.dtos.PlaceOrderDto;
import dev.nilptr.desafio.services.ProcessPaymentService;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessPaymentWorker {
    private final ProcessPaymentService processPaymentService;

    @JobWorker(type = "processPayment", autoComplete = false)
    public void handleProcessPayment(JobClient client, ActivatedJob job, @VariablesAsType PlaceOrderDto placeOrderDto) {
        log.info("Received variables " + placeOrderDto.toString());
        processPaymentService.processPayment(placeOrderDto).flatMap(
                dto -> Mono.fromCompletionStage(client.newCompleteCommand(job.getKey()).variables(dto.toVariableMap()).send())).doOnError(throwable -> {
            log.error("Failed to complete job: " + throwable.getMessage());
            client.newFailCommand(job.getKey()).retries(job.getRetries() - 1).errorMessage(throwable.getMessage()).send().exceptionally(failThrowable -> {
                log.error("Failed to fail job: " + failThrowable.getMessage());
                return null;
            });
        }).subscribe();
    }
}