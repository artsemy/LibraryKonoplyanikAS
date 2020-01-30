package by.training.epam.service.impl;

import by.training.epam.bean.Book;
import by.training.epam.dao.LibraryDAO;
import by.training.epam.dao.exception.BadFileLibraryDAOException;
import by.training.epam.data.ClientRole;
import by.training.epam.data.CurrentClientHolder;
import by.training.epam.service.BookService;
import by.training.epam.service.exception.BadFileBookServiceException;
import by.training.epam.dao.impl.LibraryDAOImpl;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.validator.BookValidator;
import by.training.epam.service.validator.impl.BookValidatorImpl;

import java.util.Collection;

import static by.training.epam.data.Constant.*;

public class BookServiceImpl implements BookService {

    private static BookServiceImpl instance;
    private LibraryDAO libraryDAO;
    private BookValidator bookValidator;

    private final static String ADDED = "added";
    private final static String DELETED = "deleted";
    private final static String CHANGED = "changed";
    private final static String FOUNDED = "founded";
    private final static String SUCCESS = "book ";
    private final static String NOT_SUCCESS = "error, book not ";

    private BookServiceImpl() throws BadFileBookServiceException {
        try {
            libraryDAO = LibraryDAOImpl.getInstance();
            bookValidator = BookValidatorImpl.getInstance();
        } catch (BadFileLibraryDAOException e) {
            throw new BadFileBookServiceException(e.getMessage(), e);
        }
    }

    public static synchronized BookServiceImpl getInstance() throws BadFileBookServiceException {
        if (instance == null) {
            instance = new BookServiceImpl();
        }
        return instance;
    }

    @Override
    public String create(String sBook) throws ServiceException {
        boolean needUpdate = false;
        if (checkRole(ClientRole.USER, ClientRole.ADMIN)) {
            Book book = bookValidator.validateCreate(sBook);
            try {
                needUpdate = libraryDAO.create(book);
            } catch (BadFileLibraryDAOException e) {
                throw new BadFileBookServiceException(MESSAGE_CANT_READ, e);
            }
        }
        return result(ADDED, needUpdate);
    }

    @Override
    public String delete(String sId) throws ServiceException {
        boolean needUpdate = false;
        if (checkRole(ClientRole.ADMIN)) {
            int id = bookValidator.validateDelete(sId);
            try {
                needUpdate = libraryDAO.delete(id);
            } catch (BadFileLibraryDAOException e) {
                throw new BadFileBookServiceException(MESSAGE_CANT_READ, e);
            }
        }
        return result(DELETED, needUpdate);
    }

    @Override
    public String update(String sBook) throws ServiceException {
        boolean needUpdate = false;
        if (checkRole(ClientRole.ADMIN)) {
            Book book = bookValidator.validateUpdate(sBook);
            try {
                needUpdate = libraryDAO.update(book);
            } catch (BadFileLibraryDAOException e) {
                throw new BadFileBookServiceException(MESSAGE_CANT_READ, e);
            }
        }
        return result(CHANGED, needUpdate);
    }

    @Override
    public String read(String sBook) {
        Book book = bookValidator.validateRead(sBook);
        Collection<Book> lib = libraryDAO.read(book);
        StringBuilder res = new StringBuilder(FOUNDED + END_LINE);
        for (Book b: lib) {
            String line = String.join(DIVIDER_BOOK_LINE, b.getTitle(), b.getAuthor(), String.valueOf(b.getId()));
            res.append(line).append(END_LINE);
        }
        return res.toString();
    }

    private String result(String sCommand, boolean worked) {
        String res = worked ? SUCCESS : NOT_SUCCESS;
        res = res + sCommand;
        return res;
    }

    private boolean checkRole(ClientRole ... roles) {
        ClientRole clientRole = CurrentClientHolder.getRole();
        for (ClientRole r: roles) {
            if (clientRole.equals(r)) {
                return true;
            }
        }
        return false;
    }

}
