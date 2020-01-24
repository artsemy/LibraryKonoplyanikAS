package by.training.epam.controller.command.impl;

import by.training.epam.controller.command.Command;
import by.training.epam.service.BookService;
import by.training.epam.service.impl.BookServiceImpl;

import java.io.IOException;

public class DeleteBook implements Command {

    @Override
    public String execute(String request) {
        String res = null;
        try {
            BookService bookService = new BookServiceImpl();
            res = bookService.deleteBook(request);
        } catch (IOException e) {
            e.printStackTrace(); //bad
        }
        return res;
    }

}
