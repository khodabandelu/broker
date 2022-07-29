package com.macan.cqrs.core.infrastructure;


import com.macan.cqrs.core.commands.BaseCommand;
import com.macan.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispatcher<T extends BaseCommand> {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
