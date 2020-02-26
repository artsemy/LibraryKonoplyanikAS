package by.training.epam.source.impl;

import by.training.epam.bean.Book;
import by.training.epam.source.BookSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static by.training.epam.Constant.*;

public class BookSourceImpl implements BookSource {

    private static BookSourceImpl instance;

    private BookSourceImpl(){}

    public static synchronized BookSourceImpl getInstance(){
        if (instance == null) {
            instance = new BookSourceImpl();
        }
        return instance;
    }

    @Override
    public Map<Integer, Book> read() throws IOException {
        Map<Integer, Book> bookCache = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_BOOK_FILE));
        String line;
        while ( (line = reader.readLine()) != null) {
            Book book = parseBook(line);
            if (book != null) {
                bookCache.put(book.getId(), book);
            }
        }
        return bookCache;
    }

    @Override
    public void write(Collection<Book> books) throws IOException {
        FileWriter writer = new FileWriter(PATH_TO_BOOK_FILE, false);
        for (Book book: books) {
            String str = String.join(DIVIDER_BOOK_LINE, book.getTitle(), book.getAuthor(), String.valueOf(book.getId()));
            writer.write(str + END_LINE);
        }
        writer.close();
    }

    private Book parseBook(String line) {
        if (line == null || line.equals(EMPTY_STRING)) {
            return null;
        }
        String[] array = line.split(DIVIDER_BOOK_LINE);
        return new Book(array[0], array[1], Integer.parseInt(array[2]));
    }

}
