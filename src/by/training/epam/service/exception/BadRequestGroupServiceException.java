package by.training.epam.service.exception;

public class BadRequestGroupServiceException extends ServiceException{

    private static final long serialVersionUID = 1L;

    public BadRequestGroupServiceException(String message) {
        super(message);
    }

    public BadRequestGroupServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestGroupServiceException() {
        super();
    }

    public BadRequestGroupServiceException(Throwable cause) {
        super(cause);
    }
}
