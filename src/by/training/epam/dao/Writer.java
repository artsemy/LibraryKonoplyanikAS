package by.training.epam.dao;

import by.training.epam.bean.Book;
import by.training.epam.bean.Client;
import by.training.epam.dao.impl.Group;
import by.training.epam.dao.impl.Library;
import by.training.epam.data.Constant;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer {

    public static void writeFileBook(String fileName, Library library) throws IOException {
        FileWriter writer = new FileWriter(fileName, false);
        List<Book> list = library.getBookList();
        for (Book book: list) {
            String divider = Constant.dividerBookLine;
            String end = Constant.endLine;
            String str = book.getTitle() + divider + book.getAuthor() + end;
            writer.write(str);
        }
        writer.close();
    }

    public static void writeFileUser(String fileName, Group group) throws IOException {
        FileWriter writer = new FileWriter(fileName, false);
        List<Client> list = group.getClientList();
        for (Client c: list) {
            String divider = Constant.dividerLine;
            String end = Constant.endLine;
            String str = String.join(divider, c.getLogin(), c.getPassword(), c.getClientRole().toString(), end);
            writer.write(str);
        }
        writer.close();
    }

}
