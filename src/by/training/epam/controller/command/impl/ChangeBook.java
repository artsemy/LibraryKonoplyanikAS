package by.training.epam.controller.command.impl;

import by.training.epam.bean.Book;
import by.training.epam.controller.command.Command;
import by.training.epam.data.Constant;
import by.training.epam.service.BookService;

import java.io.IOException;

import static by.training.epam.data.Constant.EMPTY_STRING;

public class ChangeBook implements Command {

    @Override
    public String execute(String request) {
        int id = Integer.parseInt(getId(request));
        Book book = getBook(request);
        String response = null;
        try {
            BookService bookService = new BookService();
            response = bookService.changeBook(book, id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getId(String str) {
        String[] array = str.split(Constant.DIVIDER_LINE);
        return array[0];
    }

    private Book getBook(String str) {
        String id = getId(str);
        str = str.replace(id, EMPTY_STRING);
        return new Book(str);
    }

}
