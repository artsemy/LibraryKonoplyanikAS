package by.training.epam.controller.command.impl;

import by.training.epam.controller.command.Command;
import by.training.epam.service.BookService;
import by.training.epam.service.exception.BadFileBookServiceException;
import by.training.epam.service.exception.BadRequestBookServiceException;
import by.training.epam.service.impl.BookServiceImpl;


public class DeleteBook implements Command {

    @Override
    public String execute(String request) throws BadFileBookServiceException, BadRequestBookServiceException {
        String res;
        BookService bookService = BookServiceImpl.getInstance();
        res = bookService.delete(request);
        return res;
    }

}
