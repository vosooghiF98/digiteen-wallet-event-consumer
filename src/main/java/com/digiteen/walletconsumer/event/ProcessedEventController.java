package com.digiteen.walletconsumer.event;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/processed-events")
public class ProcessedEventController {
    private final ProcessedEventRepository repository;

    public ProcessedEventController(ProcessedEventRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @GetMapping("/transaction/{transactionId}/count")
    public long countByTransaction(@PathVariable UUID transactionId) {
        return repository.countByTransactionId(transactionId);
    }

    @GetMapping("/trace/{traceId}")
    public List<ProcessedEvent> byTrace(@PathVariable String traceId) {
        return repository.findByTraceIdOrderByProcessedAtAsc(traceId);
    }
}
