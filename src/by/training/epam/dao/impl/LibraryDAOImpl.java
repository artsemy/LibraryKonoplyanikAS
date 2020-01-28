package by.training.epam.dao.impl;

import by.training.epam.bean.Book;
import by.training.epam.dao.LibraryDAO;
import by.training.epam.dao.exception.BadFileLibraryDAOException;
import by.training.epam.util.Reader;
import by.training.epam.util.Writer;

import java.io.IOException;
import java.util.*;

import static by.training.epam.data.Constant.*;

public class LibraryDAOImpl implements LibraryDAO {

    private static LibraryDAOImpl instance;
    private static Map<Integer, Book> booksCache;
    private static final int MAX_ID = 100;

    private LibraryDAOImpl() throws BadFileLibraryDAOException {
        download();
    }

    public static synchronized LibraryDAOImpl getInstance() throws BadFileLibraryDAOException {
        if (instance == null) {
            instance = new LibraryDAOImpl();
        }
        return instance;
    }

    public Map<Integer, Book> getBookMap() {
        return booksCache;
    }

    private static void download() throws BadFileLibraryDAOException {
        booksCache = new TreeMap<>();
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
        download();
        if (book == null || booksCache.containsValue(book)) {
            return false;
        }
        int id = createID();
        book.setId(id);
        booksCache.put(id, book);
        upload();
        return true;
    }


    @Override
    public boolean delete(int id) throws BadFileLibraryDAOException {
        download();
        if (booksCache.containsKey(id)) {
            booksCache.remove(id);
            upload();
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Book book) throws BadFileLibraryDAOException {
        if (booksCache.containsKey(book.getId())) {
            booksCache.replace(book.getId(), book);
            upload();
            return true;
        }
        return false;
    }

    @Override
    public boolean read(Book book) {
        String title = book.getTitle();
        String author = book.getAuthor();
        booksCache = new TreeMap<>();
        Map<Integer, Book> foundByTitle = findLibByTitle(title);
        Map<Integer, Book> foundByAuthor = findLibByAuthor(author);
        for (Book b: foundByTitle.values()) {
            if (foundByAuthor.containsKey(b.getId())) {
                booksCache.put(b.getId(), b);
            }
        }
        return !booksCache.isEmpty();
    }


    private Map<Integer, Book> findLibByTitle(String title) {
        Map<Integer, Book> findList = new TreeMap<>();
        if (title == null || title.equals(EMPTY_STRING)) {
            return booksCache;
        }
        for (Book b: booksCache.values()) {
            if (b.getTitle().contains(title)) {
                findList.put(b.getId(), b);
            }
        }
        return findList;
    }

    private Map<Integer, Book> findLibByAuthor(String author) {
        Map<Integer, Book> findList = new TreeMap<>();
        if (author == null || author.equals(EMPTY_STRING)) {
            return booksCache;
        }
        for (Book b: booksCache.values()) {
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
        } while (!booksCache.containsKey(id));
        return id;
    }

}
