# wallet-event-consumer

Standalone one-command startup: `docker compose up --build -d` from this directory.

Consumes `wallet.transactions` using Kafka. Every event is stored once in `processed_event`; the event id is the primary key, making replay idempotent.

## Run from IntelliJ
1. Start PostgreSQL and Kafka from root: `docker compose up -d postgres kafka`.
2. Run `WalletEventConsumerApplication`.

Defaults: PostgreSQL `localhost:5432/consumerdb`, Kafka `localhost:9092`, HTTP `8081`.
