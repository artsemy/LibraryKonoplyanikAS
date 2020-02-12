package by.training.epam.controller.command.exception;

public class ControllerException extends Exception {

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerException() {
        super();
    }

    public ControllerException(Throwable cause) {
        super(cause);
    }
}
