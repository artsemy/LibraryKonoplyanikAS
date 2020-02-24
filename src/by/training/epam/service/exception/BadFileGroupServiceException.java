package by.training.epam.service.exception;

public class BadFileGroupServiceException extends ServiceException {

    private static final long serialVersionUID = 1L;

    public BadFileGroupServiceException(String message) {
        super(message);
    }

    public BadFileGroupServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadFileGroupServiceException() {
        super();
    }

    public BadFileGroupServiceException(Throwable cause) {
        super(cause);
    }
}
