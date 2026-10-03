package com.digiteen.walletconsumer.event;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "processed_event")
public class ProcessedEvent {
    @Id
    private UUID id;
    @Column(name = "event_type", nullable = false, length = 100)
    private String eventType;
    @Column(name = "transaction_id", nullable = false, unique = true)
    private UUID transactionId;
    @Column(name = "trace_id", nullable = false, length = 100)
    private String traceId;
    @Column(nullable = false, columnDefinition = "text")
    private String payload;
    @Column(name = "processed_at", nullable = false)
    private Instant processedAt;

    protected ProcessedEvent() {
    }

    public ProcessedEvent(UUID id, String eventType, UUID transactionId, String traceId, String payload, Instant processedAt) {
        this.id = id;
        this.eventType = eventType;
        this.transactionId = transactionId;
        this.traceId = traceId;
        this.payload = payload;
        this.processedAt = processedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getEventType() {
        return eventType;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public String getTraceId() {
        return traceId;
    }

    public String getPayload() {
        return payload;
    }

    public Instant getProcessedAt() {
        return processedAt;
    }
}
