package com.digiteen.walletconsumer.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProcessedEventRepository extends JpaRepository<ProcessedEvent, UUID> {
    boolean existsById(UUID id);

    long countByTransactionId(UUID transactionId);

    List<ProcessedEvent> findByTraceIdOrderByProcessedAtAsc(String traceId);
}
