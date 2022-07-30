package com.macan.account.query.infrastructure.handlers;

import com.macan.account.common.events.AccountClosedEvent;
import com.macan.account.common.events.AccountOpenedEvent;
import com.macan.account.common.events.FundsDepositedEvent;
import com.macan.account.common.events.FundsWithdrawnEvent;

public interface EventHandler {
    void on(AccountOpenedEvent event);

    void on(FundsDepositedEvent event);

    void on(FundsWithdrawnEvent event);

    void on(AccountClosedEvent event);
}
