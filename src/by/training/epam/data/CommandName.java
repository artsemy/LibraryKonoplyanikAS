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
        if (command == null) {
            return null;// вы уже знаете основу работу с исключениями, зачем такой перефин ушами
        }
        CommandName[] array = CommandName.values();
        for (CommandName name: array) {
            if (command.equalsIgnoreCase(name.name())) {
                return name;
            }
        }
        return null;// здесь лучше сделать страходку и null не возвращать, вернуть команду по умолчанию, или что-то в этом роде
    }

}
