package com.macan.account.cmd.api.controllers;

import com.macan.account.cmd.api.commands.OpenAccountCommand;
import com.macan.account.cmd.api.dto.OpenAccountResponse;
import com.macan.account.common.dto.BaseResponse;
import com.macan.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/openBrokerAccount")
public class OpenAccountController {
    private final Logger logger = Logger.getLogger(OpenAccountController.class.getName());

    @Autowired
    CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> openAccount(@RequestBody OpenAccountCommand command) {
        var id = UUID.randomUUID().toString();
        command.setId(id);
        try {
            commandDispatcher.send(command);
            return new ResponseEntity<>(new OpenAccountResponse("Broker Account creation request completed successfully!", id), HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var safeErrMessage = MessageFormat.format("Error while processing request to open new broker account for id = {0} ", id);
            logger.log(Level.SEVERE, safeErrMessage);
            return new ResponseEntity<>(new OpenAccountResponse(safeErrMessage, id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
