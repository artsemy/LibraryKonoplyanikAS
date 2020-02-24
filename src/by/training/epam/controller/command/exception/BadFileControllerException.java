package by.training.epam.controller.command.exception;

public class BadFileControllerException extends ControllerException{

    private static final long serialVersionUID = 1L;

    public BadFileControllerException(String message) {
        super(message);
    }

    public BadFileControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadFileControllerException() {
        super();
    }

    public BadFileControllerException(Throwable cause) {
        super(cause);
    }
}
