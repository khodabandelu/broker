package com.example.account.cmd.api.commands;

import com.macan.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class DepositFundsCommand extends BaseCommand {
    private double amount;
}
