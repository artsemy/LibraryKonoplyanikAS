package by.training.epam.dao;

import by.training.epam.bean.Book;
import by.training.epam.bean.Client;
import by.training.epam.dao.impl.Group;
import by.training.epam.dao.impl.Library;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {

    public static void writeFileBook(String fileName, Library library) throws IOException {
        FileWriter writer = new FileWriter(fileName, false);
        ArrayList<Book> list = library.getBookList();
        for (Book book: list) {
            String str = book.getTitle() + " by " + book.getAuthor() + "\n";
            writer.write(str);
        }
        writer.close();
    }

    public static void writeFileUser(String fileName, Group group) throws IOException {
        FileWriter writer = new FileWriter(fileName, false);
        ArrayList<Client> list = group.getClientList();
        for (Client c: list) {
            String str = c.getLogin() + " " + c.getPassword() + " " + c.getClientRole() + "\n";
            writer.write(str);
        }
        writer.close();
    }

}
