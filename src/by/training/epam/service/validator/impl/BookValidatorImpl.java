package by.training.epam.service.validator.impl;

import by.training.epam.bean.Book;
import by.training.epam.service.validator.BookValidator;

import static by.training.epam.data.Constant.*;

public class BookValidatorImpl implements BookValidator {

    private static final int ID = -1;
    private static BookValidatorImpl instance;

    private BookValidatorImpl(){}

    public static synchronized BookValidatorImpl getInstance(){
        if (instance == null) {
            instance = new BookValidatorImpl();
        }
        return instance;
    }

    @Override
    public Book validateRead(String sBook) {
        if (emptyCheck(sBook)) {
            return new Book(EMPTY_STRING, EMPTY_STRING, ID);
        }
        sBook = " " + sBook; //bad
        String[] array = sBook.split(DIVIDER_BOOK_LINE);
        String title = array[0];
        String author = EMPTY_STRING;
        if (array.length == 2) {
            author = array[1];
        }
        return new Book(title.trim(), author, ID);
    }

    @Override
    public Book validateCreate(String sBook) {
        if (emptyCheck(sBook)) {
            return null;
        }
        String[] array = sBook.split(DIVIDER_BOOK_LINE);
        if (array.length == 1) {
            return null;
        }
        return new Book(array[0], array[1], ID);
    }

    @Override
    public Book validateUpdate(String sBook) {
        if (emptyCheck(sBook)) {
            return null;
        }
        String[] array = sBook.split(DIVIDER_BOOK_LINE);
        if (array.length !=3) {
            return null;
        }
        int id = validateInt(array[2]);
        if (id == ID) {
            return null;
        }
        return new Book(array[0], array[1], id);
    }

    @Override
    public int validateDelete(String sId) {
        return validateInt(sId);
    }

    private int validateInt(String str) {
        if (emptyCheck(str)) {
            return ID;
        }
        int id;
        try {
            id = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return ID;
        }
        return id;
    }

    private static boolean emptyCheck(String str) {
        return (str == null || str.equals(EMPTY_STRING));
    }

}
