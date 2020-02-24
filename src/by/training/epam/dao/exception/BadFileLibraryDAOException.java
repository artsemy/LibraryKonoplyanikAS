package by.training.epam.dao.exception;

public class BadFileLibraryDAOException extends DAOException{
    // куда пропал serialversionUID?

    public BadFileLibraryDAOException(String message) {
        super(message);
    }

    public BadFileLibraryDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadFileLibraryDAOException() {
        super();
    }

    public BadFileLibraryDAOException(Throwable cause) {
        super(cause);
    }
}
