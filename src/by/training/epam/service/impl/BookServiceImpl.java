package by.training.epam.service.impl;

import by.training.epam.bean.Book;
import by.training.epam.dao.exception.BadFileLibraryDAOException;
import by.training.epam.data.ClientRole;
import by.training.epam.data.ClientRoleHolder;
import by.training.epam.service.BookService;
import by.training.epam.service.exception.BadFileBookServiceException;
import by.training.epam.service.exception.BadRequestBookServiceException;
import by.training.epam.dao.impl.LibraryDAOImpl;

import java.util.Collection;

import static by.training.epam.data.Constant.*;

public class BookServiceImpl implements BookService {

    private final static String ADDED = "added";
    private final static String DELETED = "deleted";
    private final static String CHANGED = "changed";
    private final static String FOUNDED = "founded";
    private final static String SUCCESS = "book ";
    private final static String NOT_SUCCESS = "error, book not ";

    public BookServiceImpl() {
    }

    @Override
    public String create(String sBook) throws BadFileBookServiceException, BadRequestBookServiceException {
        boolean needUpdate = false;
        if (checkRole(ClientRole.USER, ClientRole.ADMIN)) {
            Book book = validateBook(sBook.trim());
            try {
                needUpdate = LibraryDAOImpl.getInstance().create(book);
            } catch (BadFileLibraryDAOException e) {
                throw new BadFileBookServiceException(MESSAGE_CANT_READ, e);
            }
        }
        return result(ADDED, needUpdate);
    }

    @Override
    public String delete(String sId) throws BadFileBookServiceException, BadRequestBookServiceException {
        boolean needUpdate = false;
        if (checkRole(ClientRole.ADMIN)) {
            int id = validateId(sId.trim());
            try {
                needUpdate = LibraryDAOImpl.getInstance().delete(id);
            } catch (BadFileLibraryDAOException e) {
                throw new BadFileBookServiceException(MESSAGE_CANT_READ, e);
            }
        }
        return result(DELETED, needUpdate);
    }

    @Override
    public String update(String sBook) throws BadFileBookServiceException, BadRequestBookServiceException {
        boolean needUpdate = false;
        if (checkRole(ClientRole.ADMIN)) {
            Book book = validateBookUpdate(sBook.trim());
            try {
                needUpdate = LibraryDAOImpl.getInstance().update(book);
            } catch (BadFileLibraryDAOException e) {
                throw new BadFileBookServiceException(MESSAGE_CANT_READ, e);
            }
        }
        return result(CHANGED, needUpdate);
    }

    @Override
    public String read(String sBook) throws BadRequestBookServiceException, BadFileBookServiceException {
        Book book = validateBookFind(sBook);
        Collection<Book> lib;
        try {
            LibraryDAOImpl.getInstance().read(book);
            lib = LibraryDAOImpl.getInstance().getBookMap().values();
        } catch (BadFileLibraryDAOException e) {
            throw new BadFileBookServiceException(MESSAGE_CANT_READ, e);
        }
        StringBuilder res = new StringBuilder(FOUNDED + END_LINE);
        for (Book b: lib) {
            res.append(b.getTitle()).append(DIVIDER_BOOK_LINE).append(b.getAuthor()).append(DIVIDER_LINE).append(b.getId()).append(END_LINE);
        }
        return res.toString();
    }

    private Book validateBook(String sBook) throws BadRequestBookServiceException {
        if (sBook == null) {
            throw new BadRequestBookServiceException(MESSAGE_CANT_VALIDATE_REQUEST);
        }
        String[] array = sBook.split(DIVIDER_BOOK_LINE);
        if (array.length >= 2) {
            return new Book(array[0], array[1]);
        }
        return null;
    }

    private Book validateBookFind(String sBook) throws BadRequestBookServiceException {
        if (sBook == null) {
            throw new BadRequestBookServiceException(MESSAGE_CANT_VALIDATE_REQUEST);
        }
        String[] array = sBook.split(DIVIDER_BOOK_LINE);
        if (array.length == 1) {
            return new Book(array[0].trim(), EMPTY_STRING);
        }
        return new Book(array[0].trim(), array[1]);
    }

    private Book validateBookUpdate(String sBook) throws BadRequestBookServiceException {
        if (sBook == null) {
            throw new BadRequestBookServiceException(MESSAGE_CANT_VALIDATE_REQUEST);
        }
        String[] array = sBook.split(DIVIDER_BOOK_LINE);
        String[] array2 = array[1].split(DIVIDER_LINE);
        return new Book(array[0], array2[0], Integer.parseInt(array2[1]));
    }

    private int validateId(String sId) throws BadRequestBookServiceException {
        String[] array = sId.split(DIVIDER_LINE);
        int id;
        try {
            id = Integer.parseInt(array[0]);
        } catch (NumberFormatException e) {
            throw new BadRequestBookServiceException(MESSAGE_CANT_VALIDATE_REQUEST, e);
        }
        return id;
    }

    private String result(String sCommand, boolean worked) {
        String res = worked ? SUCCESS : NOT_SUCCESS;
        res = res + sCommand;
        return res;
    }

    private boolean checkRole(ClientRole ... roles) {
        ClientRole clientRole = ClientRoleHolder.getRole();
        for (ClientRole r: roles) {
            if (clientRole.equals(r)) {
                return true;
            }
        }
        return false;
    }

}
