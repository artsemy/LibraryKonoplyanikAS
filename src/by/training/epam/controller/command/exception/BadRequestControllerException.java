package by.training.epam.controller.command.exception;

public class BadRequestControllerException extends ControllerException {

    public BadRequestControllerException(String message) {
        super(message);
    }

    public BadRequestControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestControllerException() {
        super();
    }

    public BadRequestControllerException(Throwable cause) {
        super(cause);
    }
}
