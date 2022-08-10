package com.macan.account.cmd.api.controllers;

import com.macan.account.cmd.api.commands.DepositFundsCommand;
import com.macan.account.cmd.api.commands.WithdrawFundsCommand;
import com.macan.account.common.dto.BaseResponse;
import com.macan.cqrs.core.exceptions.AggregateNotFoundException;
import com.macan.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/withdrawFunds")
public class WithdrawFundsController {
    private final Logger logger = Logger.getLogger(WithdrawFundsController.class.getName());

    @Autowired
    CommandDispatcher commandDispatcher;

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> withdrawFunds(@PathVariable("id") String id, @RequestBody WithdrawFundsCommand command) {
        try {
            command.setId(id);
            commandDispatcher.send(command);
            return new ResponseEntity<>(new BaseResponse("Withdraw funds request completed successfully!"), HttpStatus.OK);
        } catch (IllegalStateException | AggregateNotFoundException e) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var safeErrMessage = MessageFormat.format("Error while processing request to withdraw funds from broker account with id = {0} ", id);
            logger.log(Level.SEVERE, safeErrMessage);
            return new ResponseEntity<>(new BaseResponse(safeErrMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
