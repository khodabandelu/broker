package com.macan.cqrs.core.handlers;

import com.macan.cqrs.core.domain.AggregateRoot;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregate);

    T getById(String id);
}
