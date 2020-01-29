package by.training.epam.controller.command.impl;

import by.training.epam.controller.command.Command;
import by.training.epam.service.BookService;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.impl.BookServiceImpl;


public class DeleteBook implements Command {

    private BookService bookService;

    @Override
    public String execute(String request) throws ServiceException {
        String res;
        bookService = BookServiceImpl.getInstance();
        res = bookService.delete(request);
        return res;
    }

}
