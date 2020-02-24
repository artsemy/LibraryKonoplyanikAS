package by.training.epam.dao.exception;

public class BadFileGroupDAOException extends DAOException{

    private static final long serialVersionUID = 1L;

    public BadFileGroupDAOException(String message) {
        super(message);
    }

    public BadFileGroupDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadFileGroupDAOException() {
        super();
    }

    public BadFileGroupDAOException(Throwable cause) {
        super(cause);
    }
}
