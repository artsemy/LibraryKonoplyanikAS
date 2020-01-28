package by.training.epam.dao;

import by.training.epam.bean.Book;
import by.training.epam.dao.exception.BadFileLibraryDAOException;

public interface LibraryDAO {

    boolean create(Book book) throws BadFileLibraryDAOException;
    boolean delete(int id) throws BadFileLibraryDAOException;
    boolean update(Book book, int id) throws BadFileLibraryDAOException;
    boolean read(Book book);

}
