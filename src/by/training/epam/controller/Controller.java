package by.training.epam.controller;

import by.training.epam.controller.command.Command;

public class Controller {

    public String start(String request) {
        String name, req;
        String[] array = request.split(" ");
        name = array[0];
        req = request.replace(name, "").trim();
        CommandProvider provider = new CommandProvider();
        Command command = provider.getCommand(name);
        return command.execute(req);
    }

}
