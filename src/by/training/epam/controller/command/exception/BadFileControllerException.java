package by.training.epam.controller.command.exception;

public class BadFileControllerException extends Exception{

    public BadFileControllerException(String message) {
        super(message);
    }

    public BadFileControllerException(String message, Throwable cause) {
        super(message, cause);
    }

}
