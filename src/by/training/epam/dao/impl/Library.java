package by.training.epam.dao.impl;

import by.training.epam.bean.Book;
import by.training.epam.dao.LibDAO;

import java.util.ArrayList;
import java.util.Objects;

public class Library implements LibDAO {

    private ArrayList<Book> bookList;

    public Library() {
        bookList = new ArrayList<>();
    }

    public Library(ArrayList<Book> bookList) {
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
        if (!checkBook(book)) {
            return false;
        }
        bookList.add(book);
        return true;
    }

    @Override
    public boolean deleteBook(Book book) { //delete
        return bookList.remove(book);
    }

    @Override
    public boolean changeBook(Book book, int id) { //update
        if (bookList.size() < id) {
            return false;
        }
        bookList.set(id, book);
        return true;
    }

    @Override
    public Library findLib(Book book) { //read
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
        return new Library(findList);
    }

    public Library findLibByTitle(String title) {
        ArrayList<Book> findList = new ArrayList<>();
        if (title == null || title.equals("")) {
            return new Library(bookList);
        }
        for (Book b: bookList) {
            if (b.getTitle().contains(title)) {
                findList.add(b);
            }
        }
        return new Library(findList);
    }

    public Library findLibByAuthor(String author) {
        ArrayList<Book> findList = new ArrayList<>();
        if (author == null || author.equals("")) {
            return new Library(bookList);
        }
        for (Book b: bookList) {
            if (b.getAuthor().contains(author)) {
                findList.add(b);
            }
        }
        return new Library(findList);
    }

    private boolean checkBook(Book book) {
        String s1 = book.getTitle();
        String s2 = book.getAuthor();
        if (s1 == null || s2 == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(bookList, library.bookList); //bad
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookList); //bad
    }

}
