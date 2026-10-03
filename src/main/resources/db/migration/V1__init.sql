CREATE TABLE processed_event (
    id UUID PRIMARY KEY,
    event_type VARCHAR(100) NOT NULL,
    transaction_id UUID NOT NULL UNIQUE,
    trace_id VARCHAR(100) NOT NULL,
    payload TEXT NOT NULL,
    processed_at TIMESTAMPTZ NOT NULL
);
CREATE INDEX idx_processed_event_trace_id ON processed_event(trace_id);
