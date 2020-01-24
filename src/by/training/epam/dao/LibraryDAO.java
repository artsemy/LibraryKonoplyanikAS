package by.training.epam.dao;

import by.training.epam.bean.Book;
import by.training.epam.dao.impl.LibraryDAOImpl;

public interface LibraryDAO {

    //throws exception
    boolean addBook(Book book);
    boolean deleteBook(int id);
    boolean changeBook(Book book, int id);
    LibraryDAOImpl findLib(Book book); //bad type

}
