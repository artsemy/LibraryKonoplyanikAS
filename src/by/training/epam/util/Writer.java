package by.training.epam.util;

import by.training.epam.bean.Book;
import by.training.epam.bean.Client;
import by.training.epam.dao.exception.BadFileGroupDAOException;
import by.training.epam.dao.exception.BadFileLibraryDAOException;
import by.training.epam.dao.impl.GroupDAOImplSingleton;
import by.training.epam.dao.impl.LibraryDAOImplSingleton;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import static by.training.epam.data.Constant.*;

public class Writer {

    public static void writeFileBook(String fileName) throws IOException, BadFileLibraryDAOException {
        FileWriter writer = new FileWriter(fileName, false);
        Collection<Book> list = LibraryDAOImplSingleton.getInstance().getBookMap().values();
        for (Book book: list) {
            String str = book.getTitle() + DIVIDER_BOOK_LINE + book.getAuthor() + END_LINE;
            writer.write(str);
        }
        writer.close();
    }

    public static void writeFileUser(String fileName) throws IOException, BadFileGroupDAOException {
        FileWriter writer = new FileWriter(fileName, false);
        Collection<Client> set = GroupDAOImplSingleton.getInstance().getClientSet();
        for (Client c: set) {
            String str = String.join(DIVIDER_LINE, c.getLogin(), c.getPassword(), c.getClientRole().toString(), END_LINE);
            writer.write(str);
        }
        writer.close();
    }

}
