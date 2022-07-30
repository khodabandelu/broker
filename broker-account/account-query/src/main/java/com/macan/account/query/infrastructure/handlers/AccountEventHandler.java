package com.macan.account.query.infrastructure.handlers;

import com.macan.account.common.events.AccountClosedEvent;
import com.macan.account.common.events.AccountOpenedEvent;
import com.macan.account.common.events.FundsDepositedEvent;
import com.macan.account.common.events.FundsWithdrawnEvent;
import com.macan.account.query.domains.AccountRepository;
import com.macan.account.query.domains.BrokerAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountEventHandler implements EventHandler {
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public void on(AccountOpenedEvent event) {
        var brokerAccount = BrokerAccount.builder()
                .id(event.getId())
                .accountHolder(event.getAccountHolder())
                .creationDate(event.getCreatedDate())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance())
                .build();
        accountRepository.save(brokerAccount);
    }

    @Override
    public void on(FundsDepositedEvent event) {
        var brokerAccount = accountRepository.findById(event.getId());
        if (brokerAccount.isEmpty()) {
            return;
        }
        var currentBalance = brokerAccount.get().getBalance();
        var latestBalance = currentBalance + event.getAmount();
        brokerAccount.get().setBalance(latestBalance);
        accountRepository.save(brokerAccount.get());
    }

    @Override
    public void on(FundsWithdrawnEvent event) {
        var brokerAccount = accountRepository.findById(event.getId());
        if (brokerAccount.isEmpty()) {
            return;
        }
        var currentBalance = brokerAccount.get().getBalance();
        var latestBalance = currentBalance - event.getAmount();
        brokerAccount.get().setBalance(latestBalance);
        accountRepository.save(brokerAccount.get());
    }

    @Override
    public void on(AccountClosedEvent event) {
        accountRepository.deleteById(event.getId());
    }
}
