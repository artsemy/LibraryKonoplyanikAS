package by.training.epam.controller.command.impl;

import by.training.epam.controller.command.Command;
import by.training.epam.service.BookService;
import by.training.epam.service.exception.BadFileBookServiceException;
import by.training.epam.service.exception.BadRequestBookServiceException;
import by.training.epam.service.impl.BookServiceImpl;


public class ChangeBook implements Command {

    @Override
    public String execute(String request) throws BadFileBookServiceException, BadRequestBookServiceException {
        String response;
        BookService bookService = BookServiceImpl.getInstance();
        response = bookService.update(request);
        return response;
    }

}
