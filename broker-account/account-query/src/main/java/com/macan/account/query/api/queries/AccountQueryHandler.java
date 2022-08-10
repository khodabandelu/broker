package com.macan.account.query.api.queries;

import com.macan.account.query.api.dto.EqualityType;
import com.macan.account.query.domains.AccountRepository;
import com.macan.account.query.domains.BrokerAccount;
import com.macan.cqrs.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountQueryHandler implements QueryHandler {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<BaseEntity> handle(FindAllAccountsQuery query) {
        Iterable<BrokerAccount> brokerAccounts = accountRepository.findAll();
        List<BaseEntity> brokerAccountsList = new ArrayList<>();
        brokerAccounts.forEach(brokerAccountsList::add);
        return brokerAccountsList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByIdQuery query) {
        var brokerAccount = accountRepository.findById(query.getId());
        if (brokerAccount.isEmpty()) {
            return null;
        }
        List<BaseEntity> brokerAccountsList = new ArrayList<>();
        brokerAccountsList.add(brokerAccount.get());
        return brokerAccountsList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByHolderQuery query) {
        var brokerAccount = accountRepository.findByAccountHolder(query.getAccountHolder());
        if (brokerAccount.isEmpty()) {
            return null;
        }
        List<BaseEntity> brokerAccountsList = new ArrayList<>();
        brokerAccountsList.add(brokerAccount.get());
        return brokerAccountsList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountWithBalanceQuery query) {
        List<BaseEntity> brokerAccountsList = query.getEqualityType() == EqualityType.GREATER_THAN ? accountRepository.findByBalanceGreaterThan(query.getBalance()) : accountRepository.findByBalanceLessThan(query.getBalance());
        return brokerAccountsList;
    }
}
