package by.training.epam.source;

import by.training.epam.bean.Book;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public interface BookSource {

    public Map<Integer, Book> read() throws IOException;

    public void write(Collection<Book> books) throws IOException;

}
