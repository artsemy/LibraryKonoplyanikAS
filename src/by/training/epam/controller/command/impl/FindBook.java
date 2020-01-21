package by.training.epam.controller.command.impl;

import by.training.epam.bean.Book;
import by.training.epam.controller.command.Command;
import by.training.epam.service.BookService;

import java.io.IOException;

public class FindBook implements Command {

    @Override
    public String execute(String request) {
        Book book = new Book(request);
        String response = null; //??
        try {
            response = BookService.findBook(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
