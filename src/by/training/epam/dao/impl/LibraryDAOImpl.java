package by.training.epam.dao.impl;

import by.training.epam.bean.Book;
import by.training.epam.dao.LibraryDAO;
import by.training.epam.dao.exception.BadFileLibraryDAOException;
import by.training.epam.data.ClientRole;
import by.training.epam.data.CurrentClientHolder;
import by.training.epam.source.BookSource;
import by.training.epam.source.impl.BookSourceImpl;

import java.io.IOException;
import java.util.*;

import static by.training.epam.data.Constant.*;

public class LibraryDAOImpl implements LibraryDAO {

    private static LibraryDAOImpl instance;
    private BookSource source;
    private static final Map<Integer, Book> booksCache = new TreeMap<>();
    private static final int MAX_ID = 100;

    private LibraryDAOImpl() throws BadFileLibraryDAOException {
        source = BookSourceImpl.getInstance();
        download();
    }

    public static synchronized LibraryDAOImpl getInstance() throws BadFileLibraryDAOException {
        if (instance == null) {
            instance = new LibraryDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean create(Book book) throws BadFileLibraryDAOException {
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
        if (booksCache.containsKey(id)) {
            booksCache.remove(id);
            upload();
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Book book) throws BadFileLibraryDAOException {
        if (book == null) {
            return false;
        }
        if (booksCache.containsKey(book.getId())) {
            booksCache.replace(book.getId(), book);
            upload();
            return true;
        }
        return false;
    }

    @Override
    public Collection<Book> read(Book book) {
        String title = book.getTitle();
        String author = book.getAuthor();
        Set<Book> found = new TreeSet<>();
        Set<Book> foundByTitle = (Set<Book>) findLibByTitle(title);
        Set<Book> foundByAuthor = (Set<Book>) findLibByAuthor(author);
        for (Book b: foundByTitle) {
            if (foundByAuthor.contains(b)) {
                found.add(b);
            }
        }
        return found;
    }

    @Override
    public ClientRole getRole() {
        return CurrentClientHolder.getRole();
    }

    private Collection<Book> findLibByTitle(String title) {
        Set<Book> found = new TreeSet<>();
        if (title == null || title.equals(EMPTY_STRING)) {
            found.addAll(booksCache.values());
            return found;
        }
        for (Book b: booksCache.values()) {
            if (b.getTitle().contains(title)) {
                found.add(b);
            }
        }
        return found;
    }

    private Collection<Book> findLibByAuthor(String author) {
        Set<Book> found = new TreeSet<>();
        if (author == null || author.equals(EMPTY_STRING)) {
            found.addAll(booksCache.values());
            return found;
        }
        for (Book b: booksCache.values()) {
            if (b.getAuthor().contains(author)) {
                found.add(b);
            }
        }
        return found;
    }

    private int createID() {
        Random random = new Random();
        int id;
        do {
            id = random.nextInt(MAX_ID);
        } while (booksCache.containsKey(id));
        return id;
    }

    private void download() throws BadFileLibraryDAOException {
        try {
            booksCache.putAll(source.read());
        } catch (IOException e) {
            throw new BadFileLibraryDAOException("can't read file", e);
        }
    }

    private void upload() throws BadFileLibraryDAOException {
        try {
            source.write(booksCache.values());
        } catch (IOException e) {
            throw new BadFileLibraryDAOException("can't write file", e);
        }
    }

}
