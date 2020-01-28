package by.training.epam.dao.impl;

import by.training.epam.bean.Book;
import by.training.epam.dao.LibraryDAO;
import by.training.epam.dao.exception.BadFileLibraryDAOException;
import by.training.epam.util.Reader;
import by.training.epam.util.Writer;

import java.io.IOException;
import java.util.*;

import static by.training.epam.data.Constant.*;

public class LibraryDAOImplSingleton implements LibraryDAO {

    private static Map<Integer, Book> bookMap;
    private static final int MAX_ID = 100;

    private static LibraryDAOImplSingleton instance;

    private LibraryDAOImplSingleton() throws BadFileLibraryDAOException {
        download();
    }

    public static synchronized LibraryDAOImplSingleton getInstance() throws BadFileLibraryDAOException {
        if (instance == null) {
            instance = new LibraryDAOImplSingleton();
        }
        return instance;
    }

    public Map<Integer, Book> getBookMap() {
        return bookMap;
    }

    private static void download() throws BadFileLibraryDAOException {
        bookMap = new TreeMap<>();
        try {
            Reader.readFileBook(PATH_TO_BOOK_FILE);
        } catch (IOException e) {
            throw new BadFileLibraryDAOException(MESSAGE_CANT_READ);
        }
    }

    private static void upload() throws BadFileLibraryDAOException {
        try {
            Writer.writeFileBook(PATH_TO_BOOK_FILE);
        } catch (IOException e) {
            throw new BadFileLibraryDAOException(MESSAGE_CANT_WRITE);
        }
    }

    @Override
    public boolean create(Book book) throws BadFileLibraryDAOException {
        if (book == null || bookMap.containsValue(book)) {
            return false;
        }
        download();
        int id = createID();
        book.setId(id);
        bookMap.put(id, book);
        upload();
        return true;
    }


    @Override
    public boolean delete(int id) throws BadFileLibraryDAOException {
        if (bookMap.containsKey(id)) {
            download();
            bookMap.remove(id);
            upload();
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Book book, int id) throws BadFileLibraryDAOException {
        if (bookMap.containsKey(id)) {
            bookMap.replace(id, book);
            upload();
            return true;
        }
        return false;
    }

    @Override
    public boolean read(Book book) {
        String title = book.getTitle();
        String author = book.getAuthor();
        bookMap = new TreeMap<>();
        Map<Integer, Book> foundByTitle = findLibByTitle(title);
        Map<Integer, Book> foundByAuthor = findLibByAuthor(author);
        for (Book b: foundByTitle.values()) {
            if (foundByAuthor.containsKey(b.getId())) {
                bookMap.put(b.getId(), b);
            }
        }
        return !bookMap.isEmpty();
    }


    private Map<Integer, Book> findLibByTitle(String title) {
        Map<Integer, Book> findList = new TreeMap<>();
        if (title == null || title.equals(EMPTY_STRING)) {
            return bookMap;
        }
        for (Book b: bookMap.values()) {
            if (b.getTitle().contains(title)) {
                findList.put(b.getId(), b);
            }
        }
        return findList;
    }

    private Map<Integer, Book> findLibByAuthor(String author) {
        Map<Integer, Book> findList = new TreeMap<>();
        if (author == null || author.equals(EMPTY_STRING)) {
            return bookMap;
        }
        for (Book b: bookMap.values()) {
            if (b.getAuthor().contains(author)) {
                findList.put(b.getId(), b);
            }
        }
        return findList;
    }

    private int createID() {
        Random random = new Random();
        int id;
        do {
            id = random.nextInt(MAX_ID);
        } while (!bookMap.containsKey(id));
        return id;
    }

}
