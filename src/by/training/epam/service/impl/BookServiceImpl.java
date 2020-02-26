package by.training.epam.service.impl;

import by.training.epam.bean.Book;
import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.LibraryDAO;
import by.training.epam.dao.exception.DAOException;
import by.training.epam.data.ClientRole;
import by.training.epam.service.BookService;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.validator.BookValidator;
import by.training.epam.service.validator.impl.BookValidatorImpl;

import java.util.Collection;

import static by.training.epam.data.Constant.*;

public class BookServiceImpl implements BookService {

    private final static String ADDED = "added";
    private final static String DELETED = "deleted";
    private final static String CHANGED = "changed";
    private final static String FOUNDED = "founded";
    private final static String SUCCESS = "book ";
    private final static String NOT_SUCCESS = "error, book not ";

    public BookServiceImpl() {}

    @Override
    public String create(String sBook) throws ServiceException {
        boolean needUpdate = false;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            LibraryDAO libraryDAO = daoFactory.getLibraryDAO();
            BookValidator bookValidator = BookValidatorImpl.getInstance();
            if (roleCanUseCommand(ClientRole.USER, ClientRole.ADMIN)) {
                Book book = bookValidator.validateCreate(sBook);
                needUpdate = libraryDAO.create(book);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result(ADDED, needUpdate);
    }

    @Override
    public String delete(String sId) throws ServiceException {
        boolean needUpdate = false;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            LibraryDAO libraryDAO = daoFactory.getLibraryDAO();
            BookValidator bookValidator = BookValidatorImpl.getInstance();
            if (roleCanUseCommand(ClientRole.ADMIN)) {
                int id = bookValidator.validateDelete(sId);
                needUpdate = libraryDAO.delete(id);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result(DELETED, needUpdate);
    }

    @Override
    public String update(String sBook) throws ServiceException {
        boolean needUpdate = false;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            LibraryDAO libraryDAO = daoFactory.getLibraryDAO();
            BookValidator bookValidator = BookValidatorImpl.getInstance();
            if (roleCanUseCommand(ClientRole.ADMIN)) {
                Book book = bookValidator.validateUpdate(sBook);
                needUpdate = libraryDAO.update(book);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result(CHANGED, needUpdate);
    }

    @Override
    public String read(String sBook) throws ServiceException {
        StringBuilder res;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            LibraryDAO libraryDAO = daoFactory.getLibraryDAO();
            BookValidator bookValidator = BookValidatorImpl.getInstance();
            Book book = bookValidator.validateRead(sBook);
            Collection<Book> lib = libraryDAO.read(book);
            res = new StringBuilder(FOUNDED + END_LINE);
            for (Book b: lib) {
                String line = String.join(DIVIDER_BOOK_LINE, b.getTitle(), b.getAuthor(), String.valueOf(b.getId()));
                res.append(line).append(END_LINE);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return res.toString();
    }

    private String result(String sCommand, boolean worked) {
        String res = worked ? SUCCESS : NOT_SUCCESS;
        res = res + sCommand;
        return res;
    }

    private boolean roleCanUseCommand(ClientRole ... roles) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            LibraryDAO libraryDAO = daoFactory.getLibraryDAO();
            ClientRole clientRole = libraryDAO.getRole();
            for (ClientRole r: roles) {
                if (clientRole.equals(r)) {
                    return true;
                }
            }
        } catch (DAOException e) {
            throw new ServiceException();
        }
        return false;
    }

    // зачем ты решил постоянно возвращать String?
    // String удобнее всего возвращать

}
