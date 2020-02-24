package by.training.epam.dao.exception;

public class BadFileLibraryDAOException extends DAOException{

    private static final long serialVersionUID = 1L;

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
