package by.training.epam.service.exception;

public class BadRequestBookServiceException extends Exception{

    public BadRequestBookServiceException(String message) {
        super(message);
    }

    public BadRequestBookServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
