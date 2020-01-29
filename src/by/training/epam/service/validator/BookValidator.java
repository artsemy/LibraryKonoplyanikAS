package by.training.epam.service.validator;

import by.training.epam.bean.Book;

import static by.training.epam.data.Constant.*;

public class BookValidator {

    private static final int ID = -1;

    public static Book validateRead(String sBook) {
        if (emptyCheck(sBook)) {
            return new Book(EMPTY_STRING, EMPTY_STRING, ID);
        }
        String[] array = sBook.split(DIVIDER_BOOK_LINE);
        String title = array[0];
        String author = EMPTY_STRING;
        if (array.length == 2) {
            author = array[1];
        }
        return new Book(title, author, ID);
    }

    public static Book validateCreate(String sBook) {
        if (emptyCheck(sBook)) {
            return null;
        }
        String[] array = sBook.split(DIVIDER_BOOK_LINE);
        if (array.length == 1) {
            return null;
        }
        return new Book(array[0], array[1], ID);
    }

    public static Book validateUpdate(String sBook) {
        if (emptyCheck(sBook)) {
            return null;
        }
        String[] array = sBook.split(DIVIDER_BOOK_LINE);
        if (array.length !=3) {
            return null;
        }
        int id = validateDelete(array[2]);
        if (id == ID) {
            return null;
        }
        return new Book(array[0], array[1], id);
    }

    public static int validateDelete(String sId) { //Integer type?
        if (emptyCheck(sId)) {
            return ID;
        }
        int id;
        try {
            id = Integer.parseInt(sId);
        } catch (NumberFormatException e) {
            return ID;
        }
        return id;
    }

    private static boolean emptyCheck(String str) {
        return (str == null || str.equals(EMPTY_STRING));
    }

}
