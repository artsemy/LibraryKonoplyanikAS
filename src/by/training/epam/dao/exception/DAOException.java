package by.training.epam.dao.exception;

public class DAOException extends Exception {

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException() {
        super();
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
