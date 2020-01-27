package by.training.epam.dao.impl;

import by.training.epam.bean.Book;
import by.training.epam.dao.LibraryDAO;

import static by.training.epam.data.Constant.EMPTY_STRING;

import java.util.ArrayList;

public class LibraryDAOImpl implements LibraryDAO {

    private ArrayList<Book> bookList;

    public LibraryDAOImpl() {
        bookList = new ArrayList<>();
    }

    public LibraryDAOImpl(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public boolean addBook(Book book) { //create
        if (book == null || bookList.contains(book)) {
            return false;
        }
        bookList.add(book);
        return true;
    }

    @Override
    public boolean deleteBook(int id) { //delete
        if (checkId(id)) {
            bookList.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean changeBook(Book book, int id) { //update
        if (checkId(id)) {
            bookList.set(id, book);
            return true;
        }
        return false;
    }

    @Override
    public LibraryDAOImpl findLib(Book book) { //read
        String title = book.getTitle();
        String author = book.getAuthor();
        ArrayList<Book> findList = new ArrayList<>();
        ArrayList<Book> l1 = findLibByTitle(title).bookList;
        ArrayList<Book> l2 = findLibByAuthor(author).bookList;
        for (Book b1: l1) {
            if (l2.contains(b1)) {
                findList.add(b1);
            }
        }
        return new LibraryDAOImpl(findList);
    }

    private LibraryDAOImpl findLibByTitle(String title) {
        ArrayList<Book> findList = new ArrayList<>();
        if (title == null || title.equals(EMPTY_STRING)) {
            return new LibraryDAOImpl(bookList);
        }
        for (Book b: bookList) {
            if (b.getTitle().contains(title)) {
                findList.add(b);
            }
        }
        return new LibraryDAOImpl(findList);
    }

    private LibraryDAOImpl findLibByAuthor(String author) {
        ArrayList<Book> findList = new ArrayList<>();
        if (author == null || author.equals(EMPTY_STRING)) {
            return new LibraryDAOImpl(bookList);
        }
        for (Book b: bookList) {
            if (b.getAuthor().contains(author)) {
                findList.add(b);
            }
        }
        return new LibraryDAOImpl(findList);
    }

    private boolean checkId(int id) {
        return id >= 0 && id < bookList.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryDAOImpl library = (LibraryDAOImpl) o;
        return bookList.equals(library.bookList);
    }

    @Override
    public int hashCode() {
        return bookList.hashCode();
    }

    @Override
    public String toString() {
        return "LibraryDAOImpl{" +
                "bookList=" + bookList +
                '}';
    }

}
