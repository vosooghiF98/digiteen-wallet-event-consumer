package com.digiteen.walletconsumer.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Component
public class WalletEventListener {
    private static final Logger log = LoggerFactory.getLogger(WalletEventListener.class);
    private final ObjectMapper objectMapper;
    private final ProcessedEventRepository repository;

    public WalletEventListener(ObjectMapper objectMapper, ProcessedEventRepository repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;
    }

    @KafkaListener(topics = "${app.kafka.topic}")
    @Transactional
    public void onMessage(String payload) throws Exception {
        TransactionEvent event = objectMapper.readValue(payload, TransactionEvent.class);
        MDC.put("traceId", event.traceId());
        try {
            if (repository.existsById(event.eventId())) {
                log.info("duplicate event ignored eventId={} transactionId={}", event.eventId(), event.transactionId());
                return;
            }
            repository.save(new ProcessedEvent(event.eventId(), "TRANSACTION_COMPLETED",
                    event.transactionId(), event.traceId(), payload, Instant.now()));
            log.info("event processed eventId={} transactionId={} type={} amount={}",
                    event.eventId(), event.transactionId(), event.type(), event.amount());
        } finally {
            MDC.clear();
        }
    }
}
