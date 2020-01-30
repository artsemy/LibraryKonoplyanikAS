package by.training.epam.controller.command.impl;

import by.training.epam.controller.command.Command;
import by.training.epam.service.BookService;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.impl.BookServiceImpl;

public class ChangeBook implements Command {

    private BookService bookService;

    public ChangeBook() throws ServiceException {
        bookService = BookServiceImpl.getInstance();
    }

    @Override
    public String execute(String request) throws ServiceException {
        return bookService.update(request);
    }

}
