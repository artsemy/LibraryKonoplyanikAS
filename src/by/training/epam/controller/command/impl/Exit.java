package by.training.epam.controller.command.impl;

import by.training.epam.controller.command.Command;

public class Exit implements Command {

    @Override
    public String execute(String request) {
        return "bye";
    }

}
