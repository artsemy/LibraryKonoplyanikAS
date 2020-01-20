package by.training.epam.controller;

import by.training.epam.controller.command.Command;
import by.training.epam.controller.command.CommandName;
import by.training.epam.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {

    private final Map<CommandName, Command> map = new HashMap<>();

    CommandProvider() {
        map.put(CommandName.SIGN_IN, new SignIn());
        map.put(CommandName.LOG_IN, new Registration());
        map.put(CommandName.ADD_BOOK, new AddBook());
        map.put(CommandName.FIND_BOOK, new FindBook());
        map.put(CommandName.DELETE_BOOK, new DeleteBook());
        map.put(CommandName.CHANGE_BOOK, new ChangeBook());
        map.put(CommandName.WRONG_REQUEST, new WrongRequest());
        map.put(CommandName.EXIT, new Exit());
    }

    Command getCommand(String cName){
        CommandName commandName;
        Command command;
        try {
            commandName = CommandName.valueOf(cName.toUpperCase());
            command = map.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = map.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }

}
