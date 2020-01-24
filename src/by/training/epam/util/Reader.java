package by.training.epam.util;

import by.training.epam.bean.Book;
import by.training.epam.bean.Client;
import by.training.epam.dao.impl.GroupDAOImpl;
import by.training.epam.dao.impl.LibraryDAOImpl;
import by.training.epam.data.ClientRole;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static by.training.epam.data.Constant.DIVIDER_BOOK_LINE;
import static by.training.epam.data.Constant.DIVIDER_LINE;

public class Reader {

    public static LibraryDAOImpl readFileBook(String fileName) throws IOException {
        LibraryDAOImpl library = new LibraryDAOImpl();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ( (line = reader.readLine()) != null) {
            Book book = validateBook(line);
            if (book != null) {
                library.addBook(book);
            }
        }
        return library;
    }

    public static GroupDAOImpl readFileClient(String fileName) throws IOException {
        GroupDAOImpl group = new GroupDAOImpl();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ( (line = reader.readLine()) != null) {
            Client client = validateClient(line);
            if (client != null) {
                group.addClient(client);
            }
        }
        return group;
    }

    private static Book validateBook(String line) {
        String[] array = line.split(DIVIDER_BOOK_LINE);
        if (array.length == 2) {
            return new Book(array[0], array[1]);
        }
        return null;
    }

    private static Client validateClient(String line) {
        String[] array = line.split(DIVIDER_LINE);
        if (array.length == 3) {
            String login = array[0];
            String password = array[1];
            ClientRole role;
            try {
                role = ClientRole.valueOf(array[2].toUpperCase());
            } catch (IllegalArgumentException e) {
                role = ClientRole.USER;
            }
            return new Client(login, password, role);
        }
        return null;
    }

}
