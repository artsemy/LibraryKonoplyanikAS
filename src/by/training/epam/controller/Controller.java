package by.training.epam.controller;

import by.training.epam.controller.command.Command;
import by.training.epam.controller.command.CommandProvider;
import by.training.epam.controller.command.exception.BadFileControllerException;
import by.training.epam.controller.command.exception.BadRequestControllerException;
import by.training.epam.service.exception.BadFileBookServiceException;
import by.training.epam.service.exception.BadFileGroupServiceException;
import by.training.epam.service.exception.BadRequestBookServiceException;
import by.training.epam.service.exception.BadRequestGroupServiceException;

import static by.training.epam.data.Constant.DIVIDER_LINE;
import static by.training.epam.data.Constant.EMPTY_STRING;

public class Controller {

    public String start(String request) throws BadRequestControllerException, BadFileControllerException {
        RequestSplit requestSplit = new RequestSplit(request);
        CommandProvider provider = new CommandProvider();
        Command command = provider.getCommand(requestSplit.commandName);
        String response;
        try {
            response = command.execute(requestSplit.actualRequest);
        } catch (BadFileBookServiceException | BadFileGroupServiceException e) {
            throw new BadFileControllerException(e.getMessage(), e);
        } catch (BadRequestBookServiceException | BadRequestGroupServiceException e) {
            throw new BadRequestControllerException(e.getMessage(), e);
        }
        return response;
    }

    private class RequestSplit {

        private String commandName;
        private String actualRequest;

        RequestSplit(String str) {
            String[] array = str.split(DIVIDER_LINE);
            commandName = array[0];
            actualRequest = str.replace(commandName, EMPTY_STRING); //.trim
        }
    }

}
