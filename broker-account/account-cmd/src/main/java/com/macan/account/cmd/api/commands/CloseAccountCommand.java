package com.macan.account.cmd.api.commands;

import com.macan.cqrs.core.commands.BaseCommand;
import lombok.Data;

public class CloseAccountCommand extends BaseCommand {

    public CloseAccountCommand(String id){
        super(id);
    }
}
