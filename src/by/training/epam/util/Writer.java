package by.training.epam.util;

import by.training.epam.bean.Book;
import by.training.epam.bean.Client;
import by.training.epam.dao.impl.Group;
import by.training.epam.dao.impl.Library;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static by.training.epam.data.Constant.*;

public class Writer {

    public static void writeFileBook(String fileName, Library library) throws IOException {
        FileWriter writer = new FileWriter(fileName, false);
        List<Book> list = library.getBookList();
        for (Book book: list) {
            String str = book.getTitle() + DIVIDER_BOOK_LINE + book.getAuthor() + END_LINE;
            writer.write(str);
        }
        writer.close();
    }

    public static void writeFileUser(String fileName, Group group) throws IOException {
        FileWriter writer = new FileWriter(fileName, false);
        List<Client> list = group.getClientList();
        for (Client c: list) {
            String str = String.join(DIVIDER_LINE, c.getLogin(), c.getPassword(), c.getClientRole().toString(), END_LINE);
            writer.write(str);
        }
        writer.close();
    }

}
