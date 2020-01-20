package by.training.epam.dao;

import by.training.epam.bean.Book;
import by.training.epam.bean.Client;
import by.training.epam.dao.impl.Group;
import by.training.epam.dao.impl.Library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    public static Library readFileBook(String fileName) throws IOException {
        Library library = new Library();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ( (line = reader.readLine()) != null) {
            Book book = new Book(line);
            library.addBook(book);
        }
        return library;
    }

    public static Group readFileClient(String fileName) throws IOException {
        Group group = new Group();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ( (line = reader.readLine()) != null) {
            Client client = new Client(line);
            group.addClient(client);
        }
        return group;
    }

}
