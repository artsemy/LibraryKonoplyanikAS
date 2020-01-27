package by.training.epam.service.impl;

import by.training.epam.bean.Book;
import by.training.epam.data.ClientRole;
import by.training.epam.data.ClientRoleHolder;
import by.training.epam.service.BookService;
import by.training.epam.service.exception.BadFileBookServiceException;
import by.training.epam.service.exception.BadRequestBookServiceException;
import by.training.epam.util.Reader;
import by.training.epam.util.Writer;
import by.training.epam.dao.impl.LibraryDAOImpl;

import java.io.IOException;
import java.util.List;

import static by.training.epam.data.Constant.*;

public class BookServiceImpl implements BookService {

    private final String ADDED = "added";
    private final String DELETED = "deleted";
    private final String CHANGED = "changed";
    private final String FOUNDED = "founded";
    private final String SUCCESS = "book ";
    private final String NOT_SUCCESS = "error, book not ";

    private LibraryDAOImpl library;

    public BookServiceImpl() throws BadFileBookServiceException {
        initLib();
    }

    private void initLib() throws BadFileBookServiceException {
        try {
            library = Reader.readFileBook(PATH_TO_BOOK_FILE);
        } catch (IOException e) {
            throw new BadFileBookServiceException(MESSAGE_CANT_READ, e);
        }
    }

    @Override
    public String addBook(String sBook) throws BadFileBookServiceException, BadRequestBookServiceException {
        boolean needUpdate = false;
        if (checkRole(ClientRole.USER, ClientRole.ADMIN)) {
            Book book = validateBook(sBook.trim());
            needUpdate = library.addBook(book);
            saveChangeLib(needUpdate);
        }
        return result(ADDED, needUpdate);
    }

    @Override
    public String deleteBook(String sId) throws BadFileBookServiceException, BadRequestBookServiceException {
        boolean needUpdate = false;
        if (checkRole(ClientRole.ADMIN)) {
            int id = validateId(sId.trim());
            needUpdate = library.deleteBook(id);
            saveChangeLib(needUpdate);
        }
        return result(DELETED, needUpdate);
    }

    @Override
    public String changeBook(String sBook) throws BadFileBookServiceException, BadRequestBookServiceException {
        boolean needUpdate = false;
        if (checkRole(ClientRole.ADMIN)) {
            int id = validateId(sBook.trim());
            sBook = sBook.replace(String.valueOf(id), EMPTY_STRING);
            Book book = validateBook(sBook.trim());
            needUpdate = library.changeBook(book, id);
            saveChangeLib(needUpdate);
        }
        return result(CHANGED, needUpdate);
    }

    @Override
    public String findBook(String sBook) throws BadRequestBookServiceException {
        Book book = validateBookFind(sBook);
        LibraryDAOImpl lib = library.findLib(book);
        List<Book> bookList = lib.getBookList();
        StringBuilder res = new StringBuilder(FOUNDED + END_LINE);
        int i = 0;
        for (Book b: bookList) {
            res.append(b.getTitle()).append(DIVIDER_BOOK_LINE).append(b.getAuthor()).append(DIVIDER_LINE).append(i++).append(END_LINE);
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

    private void saveChangeLib(boolean needUpdate) throws BadFileBookServiceException {
        if (needUpdate) {
            try {
                Writer.writeFileBook(PATH_TO_BOOK_FILE, library);
            } catch (IOException e) {
                throw new BadFileBookServiceException(MESSAGE_CANT_WRITE, e);
            }
        }
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
