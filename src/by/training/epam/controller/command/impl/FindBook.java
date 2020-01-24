package by.training.epam.controller.command.impl;

import by.training.epam.controller.command.Command;
import by.training.epam.service.BookService;
import by.training.epam.service.impl.BookServiceImpl;

import java.io.IOException;

public class FindBook implements Command {

    @Override
    public String execute(String request) {
        String response = null; //??
        try {
            BookService bookService = new BookServiceImpl();
            response = bookService.findBook(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
