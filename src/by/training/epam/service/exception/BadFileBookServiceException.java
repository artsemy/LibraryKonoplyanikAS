package by.training.epam.service.exception;

public class BadFileBookServiceException extends ServiceException{

    public BadFileBookServiceException(String message) {
        super(message);
    }

    public BadFileBookServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
