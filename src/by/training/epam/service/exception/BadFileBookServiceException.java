package by.training.epam.service.exception;

public class BadFileBookServiceException extends Exception{

    public BadFileBookServiceException(String message) {
        super(message);
    }

    public BadFileBookServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
