package by.training.epam.data;

public enum CommandName {
    SIGN_IN,
    LOG_IN, //registration
    ADD_BOOK,
    FIND_BOOK,
    DELETE_BOOK,
    CHANGE_BOOK,
    WRONG_REQUEST,
    EXIT;

    public static CommandName parse(String command) {
        CommandName[] array = CommandName.values();
        for (CommandName name: array) {
            if (name.toString().equals(command.toUpperCase())) {
                return name;
            }
        }
        return null;
    }

}
