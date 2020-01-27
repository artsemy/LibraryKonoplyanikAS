package by.training.epam.dao;

import by.training.epam.bean.Book;

public interface LibraryDAO {

    boolean addBook(Book book);
    boolean deleteBook(int id);
    boolean changeBook(Book book, int id);
    LibraryDAO findLib(Book book);

}
