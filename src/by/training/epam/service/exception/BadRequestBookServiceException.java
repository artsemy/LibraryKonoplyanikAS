package by.training.epam.service.exception;

public class BadRequestBookServiceException extends ServiceException{

    public BadRequestBookServiceException(String message) {
        super(message);
    }

    public BadRequestBookServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestBookServiceException() {
        super();
    }

    public BadRequestBookServiceException(Throwable cause) {
        super(cause);
    }
}
