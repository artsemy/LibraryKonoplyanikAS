package by.training.epam.controller;

public enum CommandName {
    SIGN_IN,
    LOG_IN, //registration
    ADD_BOOK,
    FIND_BOOK,
    DELETE_BOOK,
    CHANGE_BOOK,
    WRONG_REQUEST,
    EXIT;

    public static CommandName parse(String sCommand) {
        CommandName commandName;
        try {
            commandName = CommandName.valueOf(sCommand.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            commandName = CommandName.WRONG_REQUEST;
        }
        return commandName;
    }

}
