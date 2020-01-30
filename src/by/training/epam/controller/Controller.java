package by.training.epam.controller;

import by.training.epam.controller.command.Command;
import by.training.epam.controller.command.CommandProvider;
import by.training.epam.controller.command.exception.ControllerException;
import by.training.epam.service.exception.*;

import static by.training.epam.data.Constant.DIVIDER_LINE;
import static by.training.epam.data.Constant.EMPTY_STRING;

public class Controller {

    public String start(String request) throws ControllerException {
        RequestSplit requestSplit = new RequestSplit(request);
        String response;
        try {
            CommandProvider provider = new CommandProvider();
            Command command = provider.getCommand(requestSplit.commandName);
            response = command.execute(requestSplit.actualRequest);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e);
        }
        return response;
    }

    private class RequestSplit {

        private String commandName;
        private String actualRequest;

        RequestSplit(String str) {
            if (str == null) {
                commandName = null;
                actualRequest = null;
            } else {
                String[] array = str.split(DIVIDER_LINE);
                commandName = array[0];
                actualRequest = str.replace(commandName, EMPTY_STRING).trim();
            }
        }
    }

}
