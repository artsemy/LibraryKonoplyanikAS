package by.training.epam.dao;

import by.training.epam.bean.Book;
import by.training.epam.dao.exception.BadFileLibraryDAOException;
import by.training.epam.data.ClientRole;

import java.util.Collection;

public interface LibraryDAO {

    boolean create(Book book) throws BadFileLibraryDAOException;
    boolean delete(int id) throws BadFileLibraryDAOException;
    boolean update(Book book) throws BadFileLibraryDAOException;
    Collection<Book> read(Book book);
    ClientRole getRole();

}
