package by.training.epam.controller.command.impl;

import by.training.epam.controller.command.Command;

public class WrongRequest implements Command {

    @Override
    public String execute(String request) {
        return "wrong request";
    }

}
