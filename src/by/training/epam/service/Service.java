package by.training.epam.service;

import by.training.epam.bean.Book;
import by.training.epam.bean.Client;
import by.training.epam.bean.ClientRole;
import by.training.epam.dao.Reader;
import by.training.epam.dao.Writer;
import by.training.epam.dao.impl.Group;
import by.training.epam.dao.impl.Library;

import java.io.IOException;
import java.util.ArrayList;

public class Service {

    private static Library library;
    private static Group group;
    private static ClientRole clientRole = ClientRole.NO_ONE;

    private static void initLib() throws IOException {
        library = Reader.readFileBook("./resources/books.txt");
    }

    private static void initGroup() throws IOException {
        group = Reader.readFileClient("./resources/users.txt");
    }

    public static String addBook(Book book) throws IOException {
        boolean b = false;
        if (checkRole(ClientRole.USER, ClientRole.ADMIN)) {
            initLib();
            b = library.addBook(book);
            saveChangeLib(b);
        }
        return result("added", b);
    }

    public static String deleteBook(Book book) throws IOException {
        boolean b = false;
        if (checkRole(ClientRole.ADMIN)) {
            initLib();
            b = library.deleteBook(book);
            saveChangeLib(b);
        }
        return result("deleted", b);
    }

    public static String changeBook(Book book, int id) throws IOException {
        boolean b = false;
        if (checkRole(ClientRole.ADMIN)) {
            initLib();
            b = library.changeBook(book, id);
            saveChangeLib(b);
        }
        return result("changed", b);
    }

    public static String findBook(Book book) throws IOException {
        initLib();
        Library lib = library.findLib(book);
        ArrayList<Book> bookList = lib.getBookList();
        StringBuilder res = new StringBuilder("founded:\n");
        for (Book b: bookList) {
            res.append(b.getTitle()).append(" by ").append(b.getAuthor()).append("\n");
        }
        return res.toString();
    }

    private static boolean checkRole(ClientRole ... roles) {
        for (ClientRole r: roles) {
            if (clientRole.equals(r)) {
                return true;
            }
        }
        return false;
    }

    private static String result(String s, boolean b) {
        String res = b ? "book" : "error, book not ";
        res = res + s;
        return res;
    }

    private static void saveChangeLib(boolean b) throws IOException {
        if (b) {
            Writer.writeFileBook("./resources/books.txt", library);
        }
    }

    private static void saveChangeGroup(boolean b) throws IOException {
        if (b) {
            Writer.writeFileUser("./resources/users.txt", group);
        }
    }

    public static String registration(Client client) throws IOException {
        initGroup();
        ArrayList<Client> clientList = group.getClientList();
        for (Client c: clientList) {
            if (client.getLogin().equals(c.getLogin())) {
                return "can't register, bad login";
            }
        }
        client.setClientRole(ClientRole.USER);
        saveChangeGroup(true);
        clientRole = ClientRole.USER;
        return "Hello newbie";
    }

    public static String signIn(Client client) throws IOException {
        initGroup();
        ArrayList<Client> clientList = group.getClientList();
        for (Client c: clientList) {
            if (client.equals(c)) {
                clientRole = c.getClientRole();
                return "welcome back";
            }
        }
        return "bad login or password";
    }

}
