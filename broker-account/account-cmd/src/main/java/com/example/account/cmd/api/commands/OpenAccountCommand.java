package com.example.account.cmd.api.commands;

import com.macan.account.common.dto.AccountType;
import com.macan.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {
    String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
