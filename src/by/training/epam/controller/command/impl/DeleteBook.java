package by.training.epam.controller.command.impl;

import by.training.epam.bean.Book;
import by.training.epam.controller.command.Command;
import by.training.epam.service.Service;

import java.io.IOException;

public class DeleteBook implements Command {

    @Override
    public String execute(String request) {
        Book book = new Book(request);
        String res = null;
        try {
            res = Service.deleteBook(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

}
