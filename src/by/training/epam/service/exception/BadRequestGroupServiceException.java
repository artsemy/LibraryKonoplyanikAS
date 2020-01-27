package by.training.epam.service.exception;

public class BadRequestGroupServiceException extends Exception{

    public BadRequestGroupServiceException(String message) {
        super(message);
    }

    public BadRequestGroupServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
