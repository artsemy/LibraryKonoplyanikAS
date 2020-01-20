package by.training.epam.dao;

import by.training.epam.bean.Book;
import by.training.epam.dao.impl.Library;

public interface LibDAO {

    //throws exception
    boolean addBook(Book book);
    boolean deleteBook(Book book);
    boolean changeBook(Book book, int id);
    Library findLib(Book book); //bad type

}
