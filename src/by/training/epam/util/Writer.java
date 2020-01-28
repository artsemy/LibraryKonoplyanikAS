package by.training.epam.util;

import by.training.epam.bean.Book;
import by.training.epam.bean.Client;
import by.training.epam.dao.exception.BadFileLibraryDAOException;
import by.training.epam.dao.impl.GroupDAOImpl;
import by.training.epam.dao.impl.LibraryDAOImplSingleton;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static by.training.epam.data.Constant.*;

public class Writer {

    public static void writeFileBook(String fileName) throws IOException, BadFileLibraryDAOException {
        FileWriter writer = new FileWriter(fileName, false);
        Collection<Book> list = null;
        list = LibraryDAOImplSingleton.getInstance().getBookMap().values();
        for (Book book: list) {
            String str = book.getTitle() + DIVIDER_BOOK_LINE + book.getAuthor() + END_LINE;
            writer.write(str);
        }
        writer.close();
    }

    public static void writeFileUser(String fileName, GroupDAOImpl group) throws IOException {
        FileWriter writer = new FileWriter(fileName, false);
        List<Client> list = group.getClientList();
        for (Client c: list) {
            String str = String.join(DIVIDER_LINE, c.getLogin(), c.getPassword(), c.getClientRole().toString(), END_LINE);
            writer.write(str);
        }
        writer.close();
    }

}
