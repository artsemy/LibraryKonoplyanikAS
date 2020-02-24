package by.training.epam.service.exception;

public class BadRequestBookServiceException extends ServiceException{

    private static final long serialVersionUID = 1L;

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
