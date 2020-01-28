package by.training.epam.controller.command;

import by.training.epam.data.CommandName;
import by.training.epam.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private static final Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.SIGN_IN, new SignIn());
        commands.put(CommandName.LOG_IN, new Registration());
        commands.put(CommandName.ADD_BOOK, new AddBook());
        commands.put(CommandName.FIND_BOOK, new FindBook());
        commands.put(CommandName.DELETE_BOOK, new DeleteBook());
        commands.put(CommandName.CHANGE_BOOK, new ChangeBook());
        commands.put(CommandName.WRONG_REQUEST, new WrongRequest());
        commands.put(CommandName.EXIT, new Exit());
    }

    public Command getCommand(String cName){
        CommandName commandName = CommandName.parse(cName);
        Command command;
        if (commandName == null) {
            command = commands.get(CommandName.WRONG_REQUEST);
        } else {
            command = commands.get(commandName);
        }
        return command;
    }

}
