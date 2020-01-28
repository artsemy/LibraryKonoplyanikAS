package by.training.epam.util;

import by.training.epam.bean.Book;
import by.training.epam.bean.Client;
import by.training.epam.dao.GroupDAO;
import by.training.epam.dao.LibraryDAO;
import by.training.epam.dao.exception.BadFileGroupDAOException;
import by.training.epam.dao.exception.BadFileLibraryDAOException;
import by.training.epam.dao.impl.GroupDAOImplSingleton;
import by.training.epam.dao.impl.LibraryDAOImplSingleton;
import by.training.epam.data.ClientRole;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static by.training.epam.data.Constant.DIVIDER_BOOK_LINE;
import static by.training.epam.data.Constant.DIVIDER_LINE;

public class Reader {

    public static void readFileBook(String fileName) throws IOException, BadFileLibraryDAOException {
        LibraryDAO library = LibraryDAOImplSingleton.getInstance();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ( (line = reader.readLine()) != null) {
            Book book = validateBook(line);
            if (book != null) {
                library.create(book);
            }
        }
    }

    public static void readFileClient(String fileName) throws IOException, BadFileGroupDAOException {
        GroupDAO group = GroupDAOImplSingleton.getInstance();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ( (line = reader.readLine()) != null) {
            Client client = validateClient(line);
            if (client != null) {
                group.addClient(client);
            }
        }
    }

    private static Book validateBook(String line) {
        String[] array = line.split(DIVIDER_BOOK_LINE);
        if (array.length == 3) {
            return new Book(array[0], array[1], Integer.parseInt(array[2]));
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
