package by.training.epam.service;

import by.training.epam.bean.Book;
import by.training.epam.data.ClientRole;
import by.training.epam.data.ClientRoleHolder;
import by.training.epam.data.Constant;
import by.training.epam.dao.Reader;
import by.training.epam.dao.Writer;
import by.training.epam.dao.impl.Library;

import java.io.IOException;
import java.util.List;

public class BookService {

    private static Library library;

    private static void initLib() throws IOException {
        String pathFile = Constant.pathBookFile;
        library = Reader.readFileBook(pathFile);
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
        List<Book> bookList = lib.getBookList();
        StringBuilder res = new StringBuilder("founded:\n");
        for (Book b: bookList) {
            res.append(b.getTitle()).append(Constant.dividerBookLine).append(b.getAuthor()).append("\n");
        }
        return res.toString();
    }

    private static String result(String s, boolean b) {
        String res = b ? "book" : "error, book not ";
        res = res + s;
        return res;
    }

    private static void saveChangeLib(boolean b) throws IOException {
        if (b) {
            String path = Constant.pathBookFile;
            Writer.writeFileBook(path, library);
        }
    }

    private static boolean checkRole(ClientRole ... roles) {
        ClientRole clientRole = ClientRoleHolder.getRole();
        for (ClientRole r: roles) {
            if (clientRole.equals(r)) {
                return true;
            }
        }
        return false;
    }

}
