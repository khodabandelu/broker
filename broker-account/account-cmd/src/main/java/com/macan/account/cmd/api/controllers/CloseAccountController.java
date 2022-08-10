package com.macan.account.cmd.api.controllers;

import com.macan.account.cmd.api.commands.CloseAccountCommand;
import com.macan.account.common.dto.BaseResponse;
import com.macan.cqrs.core.exceptions.AggregateNotFoundException;
import com.macan.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/closeBrokerAccount")
public class CloseAccountController {
    private final Logger logger = Logger.getLogger(CloseAccountController.class.getName());

    @Autowired
    CommandDispatcher commandDispatcher;

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> openAccount(@PathVariable("id") String id) {
        try {
            commandDispatcher.send(new CloseAccountCommand(id));
            return new ResponseEntity<>(new BaseResponse("Broker Account closure request completed successfully!"), HttpStatus.OK);
        } catch (IllegalStateException | AggregateNotFoundException e) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var safeErrMessage = MessageFormat.format("Error while processing request to close broker account for id = {0} ", id);
            logger.log(Level.SEVERE, safeErrMessage);
            return new ResponseEntity<>(new BaseResponse(safeErrMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
