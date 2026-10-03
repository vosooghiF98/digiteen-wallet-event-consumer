package com.digiteen.walletconsumer.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransactionEvent(
        UUID eventId,
        UUID transactionId,
        String type,
        UUID sourceWalletId,
        UUID destinationWalletId,
        BigDecimal amount,
        String traceId,
        Instant occurredAt
) {}
