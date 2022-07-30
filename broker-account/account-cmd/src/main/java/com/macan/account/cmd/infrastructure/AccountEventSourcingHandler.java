package com.macan.account.cmd.infrastructure;

import com.macan.account.cmd.domain.AccountAggregate;
import com.macan.cqrs.core.domain.AggregateRoot;
import com.macan.cqrs.core.events.BaseEvent;
import com.macan.cqrs.core.handlers.EventSourcingHandler;
import com.macan.cqrs.core.infrastructure.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {
    @Autowired
    private EventStore eventStore;

    @Override
    public void save(AggregateRoot aggregate) {
        eventStore.saveEvents(aggregate.getId(), aggregate.getUncommittedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommitted();
    }

    @Override
    public AccountAggregate getById(String id) {
        var aggregate = new AccountAggregate();
        var events = eventStore.getEvents(id);
        if (events!=null&&!events.isEmpty()){
            aggregate.replayEvents(events);
            var latestVersion=events.stream().map(BaseEvent::getVersion).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }
}
