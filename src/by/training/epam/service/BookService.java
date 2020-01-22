package by.training.epam.service;

import by.training.epam.bean.Book;
import by.training.epam.data.ClientRole;
import by.training.epam.data.ClientRoleHolder;
import by.training.epam.util.Reader;
import by.training.epam.util.Writer;
import by.training.epam.dao.impl.Library;

import java.io.IOException;
import java.util.List;

import static by.training.epam.data.Constant.DIVIDER_BOOK_LINE;
import static by.training.epam.data.Constant.PATH_TO_BOOK_FILE;

public class BookService {

    private Library library;

    public BookService() throws IOException {
        initLib();
    }

    private void initLib() throws IOException {
        library = Reader.readFileBook(PATH_TO_BOOK_FILE);
    }

    public String addBook(Book book) throws IOException {
        boolean needUpdate = false;
        if (checkRole(ClientRole.USER, ClientRole.ADMIN)) {
            needUpdate = library.addBook(book);
            saveChangeLib(needUpdate);
        }
        return result("added", needUpdate);
    }

    public String deleteBook(Book book) throws IOException {
        boolean needUpdate = false;
        if (checkRole(ClientRole.ADMIN)) {
            needUpdate = library.deleteBook(book);
            saveChangeLib(needUpdate);
        }
        return result("deleted", needUpdate);
    }

    public String changeBook(Book book, int id) throws IOException {
        boolean needUpdate = false;
        if (checkRole(ClientRole.ADMIN)) {
            needUpdate = library.changeBook(book, id);
            saveChangeLib(needUpdate);
        }
        return result("changed", needUpdate);
    }

    public String findBook(Book book) throws IOException {
        Library lib = library.findLib(book);
        List<Book> bookList = lib.getBookList();
        StringBuilder res = new StringBuilder("founded:\n");
        for (Book b: bookList) {
            res.append(b.getTitle()).append(DIVIDER_BOOK_LINE).append(b.getAuthor()).append("\n");
        }
        return res.toString();
    }

    private String result(String s, boolean b) {
        String res = b ? "book" : "error, book not ";
        res = res + s;
        return res;
    }

    private void saveChangeLib(boolean needUpdate) throws IOException {
        if (needUpdate) {
            Writer.writeFileBook(PATH_TO_BOOK_FILE, library);
        }
    }

    private boolean checkRole(ClientRole ... roles) {
        ClientRole clientRole = ClientRoleHolder.getRole();
        for (ClientRole r: roles) {
            if (clientRole.equals(r)) {
                return true;
            }
        }
        return false;
    }

}
