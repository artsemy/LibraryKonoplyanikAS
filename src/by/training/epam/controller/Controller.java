package by.training.epam.controller;

import by.training.epam.controller.command.Command;
import by.training.epam.controller.command.CommandProvider;
import by.training.epam.data.Constant;

public class Controller {

    public String start(String request) {
        RequestSplit requestSplit = new RequestSplit(request);
        CommandProvider provider = new CommandProvider();
        Command command = provider.getCommand(requestSplit.commandName);
        return command.execute(requestSplit.actualRequest);
    }

    private class RequestSplit {

        private String commandName;
        private String actualRequest;

        RequestSplit(String str) {
            String divider = Constant.DIVIDER_LINE;
            String[] array = str.split(divider);
            commandName = array[0];
            actualRequest = str.replace(commandName, "").trim();
        }
    }

}
